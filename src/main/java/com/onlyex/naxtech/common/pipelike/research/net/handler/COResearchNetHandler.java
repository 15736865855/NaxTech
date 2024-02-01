package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityCOResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class COResearchNetHandler implements ICOResearchComputationProvider {

    private final TileEntityCOResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public COResearchNetHandler(ResearchPipeNet net, @NotNull TileEntityCOResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestCORWUt(int corwut, boolean simulate, @NotNull Collection<ICOResearchComputationProvider> seen) {
        int provided = traverseRequestCORWUt(corwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxCORWUt(@NotNull Collection<ICOResearchComputationProvider> seen) {
        return traverseMaxCORWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<ICOResearchComputationProvider> seen) {
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

    private int traverseRequestCORWUt(int corwut, boolean simulate, @NotNull Collection<ICOResearchComputationProvider> seen) {
        ICOResearchComputationProvider provider = getCOComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestCORWUt(corwut, simulate, seen);
    }

    private int traverseMaxCORWUt(@NotNull Collection<ICOResearchComputationProvider> seen) {
        ICOResearchComputationProvider provider = getCOComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxCORWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<ICOResearchComputationProvider> seen) {
        ICOResearchComputationProvider provider = getCOComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private ICOResearchComputationProvider getCOComputationProvider(@NotNull Collection<ICOResearchComputationProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        ICOResearchComputationProvider hatch = inv.getCOComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
