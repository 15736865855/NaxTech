package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntitySDIResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SDIResearchNetHandler implements ISDIResearchDataProvider {

    private final TileEntitySDIResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public SDIResearchNetHandler(ResearchPipeNet net, @NotNull TileEntitySDIResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestSDIRWUt(int sdirwut, boolean simulate, @NotNull Collection<ISDIResearchDataProvider> seen) {
        int provided = traverseRequestSDIRWUt(sdirwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxSDIRWUt(@NotNull Collection<ISDIResearchDataProvider> seen) {
        return traverseMaxSDIRWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<ISDIResearchDataProvider> seen) {
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

    private int traverseRequestSDIRWUt(int sdirwut, boolean simulate, @NotNull Collection<ISDIResearchDataProvider> seen) {
        ISDIResearchDataProvider provider = getSDIComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestSDIRWUt(sdirwut, simulate, seen);
    }

    private int traverseMaxSDIRWUt(@NotNull Collection<ISDIResearchDataProvider> seen) {
        ISDIResearchDataProvider provider = getSDIComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxSDIRWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<ISDIResearchDataProvider> seen) {
        ISDIResearchDataProvider provider = getSDIComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private ISDIResearchDataProvider getSDIComputationProvider(@NotNull Collection<ISDIResearchDataProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        ISDIResearchDataProvider hatch = inv.getSDIComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
