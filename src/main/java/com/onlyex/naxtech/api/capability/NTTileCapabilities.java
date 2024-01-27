package com.onlyex.naxtech.api.capability;

import com.onlyex.naxtech.api.capability.research.IResearchComputationProvider;

import gregtech.api.capability.IDataAccessHatch;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class NTTileCapabilities {


    @CapabilityInject(IDataAccessHatch.class)
    public static Capability<IDataAccessHatch> CAPABILITY_DATA_ACCESS = null;
    @CapabilityInject(IResearchComputationProvider.class)
    public static Capability<IResearchComputationProvider> CABABILITY_COMPUTATION_PROVIDER = null;//TODO
}
