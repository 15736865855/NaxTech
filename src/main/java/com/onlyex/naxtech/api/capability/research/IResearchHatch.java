package com.onlyex.naxtech.api.capability.research;

import gregtech.api.gui.resources.TextureArea;

public interface IResearchHatch {

    /**
     //维持(EU/t)
     */
    int getUpkeepEUt();

    /**
     //最大(EU/t)
     */
    default int getMaxEUt() {
        return getUpkeepEUt();
    }

    /**如果此组件允许将桥接到网络交换机
     */
    boolean isBridge();

    /**UI中此组件的图标。应该是13x13像素的
     */
    TextureArea getComponentIcon();
}
