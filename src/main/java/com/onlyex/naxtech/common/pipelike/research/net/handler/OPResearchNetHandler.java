package com.onlyex.naxtech.common.pipelike.research.net.handler;

import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataProvider;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchPipeNet;
import com.onlyex.naxtech.common.pipelike.research.net.ResearchRoutePath;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityOPResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class OPResearchNetHandler implements IOPResearchDataProvider {

    private final TileEntityOPResearchPipe pipe;
    private final World world;
    private final EnumFacing facing;

    private ResearchPipeNet net;

    public OPResearchNetHandler(ResearchPipeNet net, @NotNull TileEntityOPResearchPipe pipe, @Nullable EnumFacing facing) {
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
    public int requestOPRWUt(int oprwut, boolean simulate, @NotNull Collection<IOPResearchDataProvider> seen) {
        int provided = traverseRequestOPRWUt(oprwut, simulate, seen);
        if (provided > 0) setPipesActive();
        return provided;
    }

    @Override
    public int getMaxOPRWUt(@NotNull Collection<IOPResearchDataProvider> seen) {
        return traverseMaxOPRWUt(seen);
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOPResearchDataProvider> seen) {
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

    private int traverseRequestOPRWUt(int oprwut, boolean simulate, @NotNull Collection<IOPResearchDataProvider> seen) {
        IOPResearchDataProvider provider = getOPComputationProvider(seen);
        if (provider == null) return 0;
        return provider.requestOPRWUt(oprwut, simulate, seen);
    }

    private int traverseMaxOPRWUt(@NotNull Collection<IOPResearchDataProvider> seen) {
        IOPResearchDataProvider provider = getOPComputationProvider(seen);
        if (provider == null) return 0;
        return provider.getMaxOPRWUt(seen);
    }

    private boolean traverseCanBridge(@NotNull Collection<IOPResearchDataProvider> seen) {
        IOPResearchDataProvider provider = getOPComputationProvider(seen);
        if (provider == null) return true; // nothing found, so don't report a problem, just pass quietly
        return provider.canBridge(seen);
    }

    @Nullable
    private IOPResearchDataProvider getOPComputationProvider(@NotNull Collection<IOPResearchDataProvider> seen) {
        if (isNetInvalidForTraversal()) return null;

        ResearchRoutePath inv = net.getNetData(pipe.getPipePos(), facing);
        if (inv == null) return null;

        IOPResearchDataProvider hatch = inv.getOPComputationHatch();
        if (hatch == null || seen.contains(hatch)) return null;
        return hatch;
    }
}
