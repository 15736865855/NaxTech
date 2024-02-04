package com.onlyex.naxtech.api.metatileentity.multiblock;

import com.onlyex.naxtech.api.capability.IBall;
import com.onlyex.naxtech.api.capability.IBuffer;
import com.onlyex.naxtech.api.capability.ICatalyst;
import com.onlyex.naxtech.api.capability.IIndustrialMaintenance;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataHatch;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataHatch;
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

    public static final MultiblockAbility<IResearchDataHatch> RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("computation_data_reception");
    public static final MultiblockAbility<IResearchDataHatch> RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("computation_data_transmission");

    public static final MultiblockAbility<IGOResearchDataHatch> GO_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("go_computation_data_reception");
    public static final MultiblockAbility<IGOResearchDataHatch> GO_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("go_computation_data_transmission");

    public static final MultiblockAbility<IOPResearchDataHatch> OP_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("op_computation_data_reception");
    public static final MultiblockAbility<IOPResearchDataHatch> OP_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("op_computation_data_transmission");

    public static final MultiblockAbility<ISPResearchDataHatch> SP_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sp_computation_data_reception");
    public static final MultiblockAbility<ISPResearchDataHatch> SP_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sp_computation_data_transmission");

    public static final MultiblockAbility<ICOResearchDataHatch> CO_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("co_computation_data_reception");
    public static final MultiblockAbility<ICOResearchDataHatch> CO_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("co_computation_data_transmission");

    public static final MultiblockAbility<ISCAResearchDataHatch> SCA_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sca_computation_data_reception");
    public static final MultiblockAbility<ISCAResearchDataHatch> SCA_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sca_computation_data_transmission");

    public static final MultiblockAbility<ISCHResearchDataHatch> SCH_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sch_computation_data_reception");
    public static final MultiblockAbility<ISCHResearchDataHatch> SCH_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sch_computation_data_transmission");

    public static final MultiblockAbility<ISDIResearchDataHatch> SDI_RESEARCH_COMPUTATION_DATA_RECEPTION =
            new MultiblockAbility<>("sdi_computation_data_reception");
    public static final MultiblockAbility<ISDIResearchDataHatch> SDI_RESEARCH_COMPUTATION_DATA_TRANSMISSION =
            new MultiblockAbility<>("sdi_computation_data_transmission");


}
