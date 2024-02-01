package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchComputationProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityCOResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityGOResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class GOResearchNetHandler implements IGOResearchComputationProvider {

    private final TileEntityGOResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public GOResearchNetHandler(ResearchPipeNet net, TileEntityGOResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestGORWUt(int gorwut, boolean simulate, @NotNull Collection<IGOResearchComputationProvider> seen) {
        int provided = traverseRequestGORWUt(gorwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxGORWUt(@NotNull Collection<IGOResearchComputationProvider> seen) {
        return traverseMaxGORWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<IGOResearchComputationProvider> seen) {
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

    private int traverseRequestGORWUt(int gorwut, boolean simulate, @NotNull Collection<IGOResearchComputationProvider> seen) {
        IGOResearchComputationProvider provider = getGOComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestGORWUt(gorwut, simulate, seen);
    }

    private int traverseMaxGORWUt(@NotNull Collection<IGOResearchComputationProvider> seen) {
        IGOResearchComputationProvider provider = getGOComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxGORWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<IGOResearchComputationProvider> seen) {
        IGOResearchComputationProvider provider = getGOComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private IGOResearchComputationProvider getGOComputationProvider(@NotNull Collection<IGOResearchComputationProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        IGOResearchComputationProvider hatch = inv.getGOComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
