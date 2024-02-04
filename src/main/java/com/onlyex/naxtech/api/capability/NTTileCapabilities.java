package com.onlyex.naxtech.api.capability;

import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class NTTileCapabilities {
    @CapabilityInject(IResearchDataProvider.class)
    public static Capability<IResearchDataProvider> CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(IGOResearchDataProvider.class)
    public static Capability<IGOResearchDataProvider> GO_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(IOPResearchDataProvider.class)
    public static Capability<IOPResearchDataProvider> OP_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISPResearchDataProvider.class)
    public static Capability<ISPResearchDataProvider> SP_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ICOResearchDataProvider.class)
    public static Capability<ICOResearchDataProvider> CO_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISCAResearchDataProvider.class)
    public static Capability<ISCAResearchDataProvider> SCA_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISCHResearchDataProvider.class)
    public static Capability<ISCHResearchDataProvider> SCH_CABABILITY_RESEARCH_PROVIDER = null;
    @CapabilityInject(ISDIResearchDataProvider.class)
    public static Capability<ISDIResearchDataProvider> SDI_CABABILITY_RESEARCH_PROVIDER = null;
}
