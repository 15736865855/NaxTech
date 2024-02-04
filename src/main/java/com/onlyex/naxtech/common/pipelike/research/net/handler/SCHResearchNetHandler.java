package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntitySCHResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SCHResearchNetHandler implements ISCHResearchDataProvider {

    private final TileEntitySCHResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public SCHResearchNetHandler(ResearchPipeNet net, @NotNull TileEntitySCHResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestSCHRWUt(int schrwut, boolean simulate, @NotNull Collection<ISCHResearchDataProvider> seen) {
        int provided = traverseRequestSCHRWUt(schrwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxSCHRWUt(@NotNull Collection<ISCHResearchDataProvider> seen) {
        return traverseMaxSCHRWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<ISCHResearchDataProvider> seen) {
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

    private int traverseRequestSCHRWUt(int schrwut, boolean simulate, @NotNull Collection<ISCHResearchDataProvider> seen) {
        ISCHResearchDataProvider provider = getSCHComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestSCHRWUt(schrwut, simulate, seen);
    }

    private int traverseMaxSCHRWUt(@NotNull Collection<ISCHResearchDataProvider> seen) {
        ISCHResearchDataProvider provider = getSCHComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxSCHRWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<ISCHResearchDataProvider> seen) {
        ISCHResearchDataProvider provider = getSCHComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private ISCHResearchDataProvider getSCHComputationProvider(@NotNull Collection<ISCHResearchDataProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        ISCHResearchDataProvider hatch = inv.getSCHComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
