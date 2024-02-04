package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntitySCAResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SCAResearchNetHandler implements ISCAResearchDataProvider {

    private final TileEntitySCAResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public SCAResearchNetHandler(ResearchPipeNet net, @NotNull TileEntitySCAResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestSCARWUt(int scarwut, boolean simulate, @NotNull Collection<ISCAResearchDataProvider> seen) {
        int provided = traverseRequestSCARWUt(scarwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxSCARWUt(@NotNull Collection<ISCAResearchDataProvider> seen) {
        return traverseMaxSCARWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<ISCAResearchDataProvider> seen) {
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

    private int traverseRequestSCARWUt(int scarwut, boolean simulate, @NotNull Collection<ISCAResearchDataProvider> seen) {
        ISCAResearchDataProvider provider = getSCAComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestSCARWUt(scarwut, simulate, seen);
    }

    private int traverseMaxSCARWUt(@NotNull Collection<ISCAResearchDataProvider> seen) {
        ISCAResearchDataProvider provider = getSCAComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxSCARWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<ISCAResearchDataProvider> seen) {
        ISCAResearchDataProvider provider = getSCAComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private ISCAResearchDataProvider getSCAComputationProvider(@NotNull Collection<ISCAResearchDataProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        ISCAResearchDataProvider hatch = inv.getSCAComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
