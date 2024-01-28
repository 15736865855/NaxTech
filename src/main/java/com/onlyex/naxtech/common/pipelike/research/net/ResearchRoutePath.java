package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.api.capability.IDataAccessHatch;
import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.api.capability.research.IResearchComputationProvider;
import com.onlyex.naxtech.api.capability.research.IResearchDataAccessHatch;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import gregtech.api.pipenet.IRoutePath;
import net.minecraft.util.EnumFacing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResearchRoutePath implements IRoutePath<TileEntityResearchPipe> {

    private final TileEntityResearchPipe targetPipe;
    private final EnumFacing faceToHandler;
    private final int distance;

    public ResearchRoutePath(TileEntityResearchPipe targetPipe, EnumFacing faceToHandler, int distance) {
        this.targetPipe = targetPipe;
        this.faceToHandler = faceToHandler;
        this.distance = distance;
    }

    @NotNull
    @Override
    public TileEntityResearchPipe getTargetPipe() {
        return targetPipe;
    }

    @NotNull
    @Override
    public EnumFacing getTargetFacing() {
        return faceToHandler;
    }

    public int getDistance() {
        return distance;
    }

    @Nullable
    public IResearchDataAccessHatch getDataHatch() {
        IDataAccessHatch dataAccessHatch = getTargetCapability(NTTileCapabilities.CAPABILITY_DATA_ACCESS);
        return dataAccessHatch instanceof IResearchDataAccessHatch opticalHatch ? opticalHatch : null;
    }

    public @Nullable IResearchComputationProvider getComputationHatch() {
        return getTargetCapability(NTTileCapabilities.CABABILITY_COMPUTATION_PROVIDER);
    }
}
