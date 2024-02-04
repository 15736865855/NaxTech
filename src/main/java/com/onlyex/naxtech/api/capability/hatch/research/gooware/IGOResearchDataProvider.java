package com.onlyex.naxtech.api.capability.hatch.research.gooware;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于表示任何使用 Transmitter Computation Hatches（传输计算舱）的多方块结构。
 * 该接口为多方块结构提供了计算相关的功能。
 */
public interface IGOResearchDataProvider {

    /**
     * requestGORWUt(int gorwut, boolean simulate)：
     * 默认方法，实现该接口的类可以通过此方法请求每刻的最大 GORWU/t（每刻的计算工作单位）。
     * simulate 参数表示是否模拟请求，方法返回可供的 GORWU/t 数量。
     */
    default int requestGORWUt(int gorwut, boolean simulate) {
        Collection<IGOResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return requestGORWUt(gorwut, simulate, list);
    }

    /**
     * requestGORWUt(int gorwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen)：
     * 用于请求每刻的最大 GORWU/t。
     * 该方法需要实现类提供 gorwut 和 simulate 参数的处理逻辑，并返回可供的 GORWU/t 数量。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    int requestGORWUt(int gorwut, boolean simulate, @NotNull Collection<IGOResearchDataProvider> seen);

    /**
     * getMaxGORWUt()：
     * 默认方法，返回该计算提供者可以提供的最大 GORWU/t 数量。
     */
    default int getMaxGORWUt() {
        Collection<IGOResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return getMaxGORWUt(list);
    }

    /**
     * getMaxGORWUt(@NotNull Collection<IResearchComputationProvider> seen)：
     * 返回该计算提供者可以提供的最大 GORWU/t 数量。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    int getMaxGORWUt(@NotNull Collection<IGOResearchDataProvider> seen);

    /**
     * canBridge()：
     * 默认方法，返回该计算提供者是否可以与其他计算提供者“桥接”的布尔值。
     */
    default boolean canBridge() {
        Collection<IGOResearchDataProvider> list = new ArrayList<>();
        list.add(this);
        return canBridge(list);
    }

    /**
     * canBridge(@NotNull Collection<IResearchComputationProvider> seen)：
     * 返回该计算提供者是否可以与其他计算提供者“桥接”的布尔值。
     * seen 参数表示已经检查过的光学计算提供者。
     */
    boolean canBridge(@NotNull Collection<IGOResearchDataProvider> seen);
}
