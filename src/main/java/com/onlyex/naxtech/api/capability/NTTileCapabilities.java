package com.onlyex.naxtech.api.capability;

import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchComputationProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchComputationProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class NTTileCapabilities {
    @CapabilityInject(IResearchComputationProvider.class)
    public static Capability<IResearchComputationProvider> CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(IGOResearchComputationProvider.class)
    public static Capability<IGOResearchComputationProvider> GO_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(IOPResearchComputationProvider.class)
    public static Capability<IOPResearchComputationProvider> OP_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISPResearchComputationProvider.class)
    public static Capability<ISPResearchComputationProvider> SP_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ICOResearchComputationProvider.class)
    public static Capability<ICOResearchComputationProvider> CO_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISCAResearchComputationProvider.class)
    public static Capability<ISCAResearchComputationProvider> SCA_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISCHResearchComputationProvider.class)
    public static Capability<ISCHResearchComputationProvider> SCH_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISDIResearchComputationProvider.class)
    public static Capability<ISDIResearchComputationProvider> SDI_CABABILITY_RESEARCH_PROVIDER = null;
}
