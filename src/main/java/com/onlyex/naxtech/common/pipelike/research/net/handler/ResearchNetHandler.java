package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchComputationProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ResearchNetHandler implements IResearchComputationProvider {

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
    public int requestRWUt(int rwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen) {
        int provided = traverseRequestRWUt(rwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxRWUt(@NotNull Collection<IResearchComputationProvider> seen) {
        return traverseMaxRWUt(seen);
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

    private int traverseRequestRWUt(int rwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen) {
        IResearchComputationProvider provider = getComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestRWUt(rwut, simulate, seen);
    }

    private int traverseMaxRWUt(@NotNull Collection<IResearchComputationProvider> seen) {
        IResearchComputationProvider provider = getComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxRWUt(seen);
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
