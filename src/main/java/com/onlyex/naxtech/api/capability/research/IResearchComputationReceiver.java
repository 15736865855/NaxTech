package com.onlyex.naxtech.api.capability.research;


import gregtech.api.capability.impl.ComputationRecipeLogic;

/**
 * Used in conjunction with {@link ComputationRecipeLogic}.
 */
public interface IResearchComputationReceiver {

    IResearchComputationProvider getComputationProvider();
}
