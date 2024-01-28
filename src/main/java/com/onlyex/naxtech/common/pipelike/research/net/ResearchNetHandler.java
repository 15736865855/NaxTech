package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.api.capability.research.IResearchComputationProvider;

import com.onlyex.naxtech.api.capability.research.IResearchDataAccessHatch;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import com.onlyex.naxtech.api.capability.IDataAccessHatch;
import gregtech.api.recipes.Recipe;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ResearchNetHandler implements IDataAccessHatch, IResearchComputationProvider {

    private final TileEntityResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public ResearchNetHandler(ResearchPipeNet net, @NotNull TileEntityResearchPipe pipe, @Nullable EnumFacing facing) {
        this.net = net;
        this.pipe = pipe;
        this.facing = facing;
        this.world = pipe.getWorld();
    }

    public void updateNetwork(ResearchPipeNet net) {
        this.net = net;
    }

    public ResearchPipeNet getNet() {
        return net;
    }

    @Override
    public boolean isRecipeAvailable(@NotNull Recipe recipe, @NotNull Collection<IDataAccessHatch> seen) {
        boolean isAvailable = traverseRecipeAvailable(recipe, seen);
        if (isAvailable) setPipesActive();
        return isAvailable;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen) {
        int provided = traverseRequestCWUt(cwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IResearchComputationProvider> seen) {
        return traverseMaxCWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<IResearchComputationProvider> seen) {
        return traverseCanBridge(seen);
    }

    private void setPipesActive() {
        for (BlockPos pos : net.getAllNodes().keySet()) {
            if (world.getTileEntity(pos) instanceof TileEntityResearchPipe ResearchPipe) {
                ResearchPipe.setActive(true, 100);
            }
        }
    }

    private boolean isNetInvalidForTraversal() {
        return net == null || pipe == null || pipe.isInvalid();
    }

    private boolean traverseRecipeAvailable(@NotNull Recipe recipe, @NotNull Collection<IDataAccessHatch> seen) {
        if (isNetInvalidForTraversal()) return false;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return false;

        IResearchDataAccessHatch hatch = inv.getDataHatch();
        if (hatch == null || seen.contains(hatch)) return false;

        if (hatch.isTransmitter()) {
            return hatch.isRecipeAvailable(recipe, seen);
        }
        return false;
    }

    private int traverseRequestCWUt(int cwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen) {
        IResearchComputationProvider provider = getComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestCWUt(cwut, simulate, seen);
    }

    private int traverseMaxCWUt(@NotNull Collection<IResearchComputationProvider> seen) {
        IResearchComputationProvider provider = getComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxCWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<IResearchComputationProvider> seen) {
        IResearchComputationProvider provider = getComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private IResearchComputationProvider getComputationProvider(@NotNull Collection<IResearchComputationProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        IResearchComputationProvider hatch = inv.getComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
