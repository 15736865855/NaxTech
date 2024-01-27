package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.pipenet.PipeNetWalker;
import gregtech.api.util.GTUtility;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

public class ResearchNetWalker extends PipeNetWalker<TileEntityResearchPipe> {

    public static final ResearchRoutePath FAILED_MARKER = new ResearchRoutePath(null, null, 0);

    @Nullable
    public static ResearchRoutePath createNetData(World world, BlockPos sourcePipe, EnumFacing faceToSourceHandler) {
        ResearchNetWalker walker = new ResearchNetWalker(world, sourcePipe, 1);
        walker.sourcePipe = sourcePipe;
        walker.facingToHandler = faceToSourceHandler;
        walker.traversePipeNet();
        return walker.isFailed() ? FAILED_MARKER : walker.routePath;
    }

    private ResearchRoutePath routePath;
    private BlockPos sourcePipe;
    private EnumFacing facingToHandler;

    protected ResearchNetWalker(World world, BlockPos sourcePipe, int distance) {
        super(world, sourcePipe, distance);
    }

    @Override
    protected PipeNetWalker<TileEntityResearchPipe> createSubWalker(World world, EnumFacing facingToNextPos,
                                                                   BlockPos nextPos, int walkedBlocks) {
        ResearchNetWalker walker = new ResearchNetWalker(world, nextPos, walkedBlocks);
        walker.facingToHandler = facingToHandler;
        walker.sourcePipe = sourcePipe;
        return walker;
    }

    @Override
    protected void checkPipe(TileEntityResearchPipe pipeTile, BlockPos pos) {}

    @Override
    protected void checkNeighbour(TileEntityResearchPipe pipeTile, BlockPos pipePos, EnumFacing faceToNeighbour,
                                  @Nullable TileEntity neighbourTile) {
        if (neighbourTile == null ||
                (GTUtility.arePosEqual(pipePos, sourcePipe) && faceToNeighbour == facingToHandler)) {
            return;
        }

        if (((ResearchNetWalker) root).routePath == null) {
            if (neighbourTile.hasCapability(NTTileCapabilities.CAPABILITY_DATA_ACCESS,
                    faceToNeighbour.getOpposite()) ||
                    neighbourTile.hasCapability(NTTileCapabilities.CABABILITY_COMPUTATION_PROVIDER,
                            faceToNeighbour.getOpposite())) {
                ((ResearchNetWalker) root).routePath = new ResearchRoutePath(pipeTile, faceToNeighbour,
                        getWalkedBlocks());
                stop();
            }
        }
    }

    @Override
    protected Class<TileEntityResearchPipe> getBasePipeClass() {
        return TileEntityResearchPipe.class;
    }
}
