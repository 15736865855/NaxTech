package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataProvider;
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

    public @Nullable IResearchDataProvider getComputationHatch() {
        return getTargetCapability(NTTileCapabilities.CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable IGOResearchDataProvider getGOComputationHatch() {
        return getTargetCapability(NTTileCapabilities.GO_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable IOPResearchDataProvider getOPComputationHatch() {
        return getTargetCapability(NTTileCapabilities.OP_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISPResearchDataProvider getSPComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SP_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ICOResearchDataProvider getCOComputationHatch() {
        return getTargetCapability(NTTileCapabilities.CO_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISCAResearchDataProvider getSCAComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SCA_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISCHResearchDataProvider getSCHComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SCH_CABABILITY_RESEARCH_PROVIDER);
    }

    public @Nullable ISDIResearchDataProvider getSDIComputationHatch() {
        return getTargetCapability(NTTileCapabilities.SDI_CABABILITY_RESEARCH_PROVIDER);
    }
}
