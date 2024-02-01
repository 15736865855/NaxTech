package com.onlyex.naxtech.api.metatileentity.multiblock;

import com.onlyex.naxtech.api.capability.IBall;
import com.onlyex.naxtech.api.capability.IBuffer;
import com.onlyex.naxtech.api.capability.ICatalyst;
import com.onlyex.naxtech.api.capability.IIndustrialMaintenance;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchComputationHatch;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;

@SuppressWarnings("InstantiationOfUtilityClass")
public class NTMultiblockAbility {
    public static final MultiblockAbility<IBuffer> BUFFER_MULTIBLOCK_ABILITY =
            new MultiblockAbility<>("buffer");
    public static final MultiblockAbility<IBall> GRINDBALL_MULTIBLOCK_ABILITY =
            new MultiblockAbility<>("ball");
    public static final MultiblockAbility<ICatalyst> CATALYST_MULTIBLOCK_ABILITY =
            new MultiblockAbility<>("catalyst");
    public static final MultiblockAbility<IIndustrialMaintenance> INDUSTRIAL_MAINTENANCE_MULTIBLOCK_ABILITY =
            new MultiblockAbility<>("industrial_maintenance");

    public static final MultiblockAbility<IResearchComputationHatch> RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("computation_data_reception");
    public static final MultiblockAbility<IResearchComputationHatch> RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("computation_data_transmission");

    public static final MultiblockAbility<IGOResearchComputationHatch> GO_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("go_computation_data_reception");
    public static final MultiblockAbility<IGOResearchComputationHatch> GO_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("go_computation_data_transmission");

    public static final MultiblockAbility<IOPResearchComputationHatch> OP_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("op_computation_data_reception");
    public static final MultiblockAbility<IOPResearchComputationHatch> OP_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("op_computation_data_transmission");

    public static final MultiblockAbility<ISPResearchComputationHatch> SP_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sp_computation_data_reception");
    public static final MultiblockAbility<ISPResearchComputationHatch> SP_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sp_computation_data_transmission");

    public static final MultiblockAbility<ICOResearchComputationHatch> CO_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("co_computation_data_reception");
    public static final MultiblockAbility<ICOResearchComputationHatch> CO_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("co_computation_data_transmission");

    public static final MultiblockAbility<ISCAResearchComputationHatch> SCA_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sca_computation_data_reception");
    public static final MultiblockAbility<ISCAResearchComputationHatch> SCA_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sca_computation_data_transmission");

    public static final MultiblockAbility<ISCHResearchComputationHatch> SCH_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sch_computation_data_reception");
    public static final MultiblockAbility<ISCHResearchComputationHatch> SCH_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sch_computation_data_transmission");

    public static final MultiblockAbility<ISDIResearchComputationHatch> SDI_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sdi_computation_data_reception");
    public static final MultiblockAbility<ISDIResearchComputationHatch> SDI_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sdi_computation_data_transmission");


}
