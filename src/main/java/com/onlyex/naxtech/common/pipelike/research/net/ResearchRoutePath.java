package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchComputationProvider;
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

    public @Nullable IResearchComputationProvider getComputationHatch() {
        return getTargetCapability(NTTileCapabilities.CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable IGOResearchComputationProvider getGOComputationHatch() {
        return getTargetCapability(NTTileCapabilities.GO_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable IOPResearchComputationProvider getOPComputationHatch() {
        return getTargetCapability(NTTileCapabilities.OP_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISPResearchComputationProvider getSPComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SP_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ICOResearchComputationProvider getCOComputationHatch() {
        return getTargetCapability(NTTileCapabilities.CO_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISCAResearchComputationProvider getSCAComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SCA_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISCHResearchComputationProvider getSCHComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SCH_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISDIResearchComputationProvider getSDIComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SDI_CABABILITY_RESEARCH_PROVIDER);
    }
}
