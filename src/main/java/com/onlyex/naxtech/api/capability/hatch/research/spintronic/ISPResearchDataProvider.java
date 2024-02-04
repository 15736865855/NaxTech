package com.onlyex.naxtech.api.capability.hatch.research.spintronic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于表示任何使用 Transmitter Computation Hatches（传输计算舱）的多方块结构。
 * 该接口为多方块结构提供了计算相关的功能。
 */
public interface ISPResearchDataProvider {

    /**
     * requestSPRWUt(int sprwut, boolean simulate)：
     * 默认方法，实现该接口的类可以通过此方法请求每刻的最大 SPRWU/t（每刻的计算工作单位）。
     * simulate 参数表示是否模拟请求，方法返回可供的 SPRWU/t 数量。
     */
    default int requestSPRWUt(int sprwut, boolean simulate) {
        Collection<ISPResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return requestSPRWUt(sprwut, simulate, list);
    }

    /**
     * requestSPRWUt(int sprwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen)：
     * 用于请求每刻的最大 SPRWU/t。
     * 该方法需要实现类提供 sprwut 和 simulate 参数的处理逻辑，并返回可供的 SPRWU/t 数量。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    int requestSPRWUt(int sprwut, boolean simulate, @NotNull Collection<ISPResearchDataProvider> seen);

    /**
     * getMaxSPRWUt()：
     * 默认方法，返回该计算提供者可以提供的最大 SPRWU/t 数量。
     */
    default int getMaxSPRWUt() {
        Collection<ISPResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return getMaxSPRWUt(list);
    }

    /**
     * getMaxSPRWUt(@NotNull Collection<IResearchComputationProvider> seen)：
     * 返回该计算提供者可以提供的最大 SPRWU/t 数量。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    int getMaxSPRWUt(@NotNull Collection<ISPResearchDataProvider> seen);

    /**
     * canBridge()：
     * 默认方法，返回该计算提供者是否可以与其他计算提供者“桥接”的布尔值。
     */
    default boolean canBridge() {
        Collection<ISPResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return canBridge(list);
    }

    /**
     * canBridge(@NotNull Collection<IResearchComputationProvider> seen)：
     * 返回该计算提供者是否可以与其他计算提供者“桥接”的布尔值。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    boolean canBridge(@NotNull Collection<ISPResearchDataProvider> seen);
}
