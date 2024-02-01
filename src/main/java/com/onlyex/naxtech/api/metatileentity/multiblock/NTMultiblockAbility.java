package com.onlyex.naxtech.api.metatileentity.multiblock;

import com.onlyex.naxtech.api.capability.IBall;
import com.onlyex.naxtech.api.capability.IBuffer;
import com.onlyex.naxtech.api.capability.ICatalyst;
import com.onlyex.naxtech.api.capability.IIndustrialMaintenance;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchComputationHatch;
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

}
