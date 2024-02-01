package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchComputationProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntitySPResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SPResearchNetHandler implements ISPResearchComputationProvider {

    private final TileEntitySPResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public SPResearchNetHandler(ResearchPipeNet net, @NotNull TileEntitySPResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestSPRWUt(int sprwut, boolean simulate, @NotNull Collection<ISPResearchComputationProvider> seen) {
        int provided = traverseRequestSPRWUt(sprwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxSPRWUt(@NotNull Collection<ISPResearchComputationProvider> seen) {
        return traverseMaxSPRWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<ISPResearchComputationProvider> seen) {
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

    private int traverseRequestSPRWUt(int sprwut, boolean simulate, @NotNull Collection<ISPResearchComputationProvider> seen) {
        ISPResearchComputationProvider provider = getSPComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestSPRWUt(sprwut, simulate, seen);
    }

    private int traverseMaxSPRWUt(@NotNull Collection<ISPResearchComputationProvider> seen) {
        ISPResearchComputationProvider provider = getSPComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxSPRWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<ISPResearchComputationProvider> seen) {
        ISPResearchComputationProvider provider = getSPComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private ISPResearchComputationProvider getSPComputationProvider(@NotNull Collection<ISPResearchComputationProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        ISPResearchComputationProvider hatch = inv.getSPComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
