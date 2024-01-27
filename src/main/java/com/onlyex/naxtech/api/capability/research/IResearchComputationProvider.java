package com.onlyex.naxtech.api.capability.research;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * MUST be implemented on any multiblock which uses
 * Transmitter Computation Hatches in its structure.
 */
public interface IResearchComputationProvider {

    /**
     * Request some amount of CWU/t (Compute Work Units per tick) from this Machine.
     * Implementors should expect these requests to occur each tick that computation is required.
     *
     * @param cwut Maximum amount of CWU/t requested.
     * @return The amount of CWU/t that could be supplied.
     */
    default int requestCWUt(int cwut, boolean simulate) {
        Collection<IResearchComputationProvider> list = new ArrayList<>();
        list.add(this);
        return requestCWUt(cwut, simulate, list);
    }

    /**
     * Request some amount of CWU/t (Compute Work Units per tick) from this Machine.
     * Implementors should expect these requests to occur each tick that computation is required.
     *
     * @param cwut Maximum amount of CWU/t requested.
     * @param seen The Optical Computation Providers already checked
     * @return The amount of CWU/t that could be supplied.
     */
    int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IResearchComputationProvider> seen);

    /**
     * The maximum of CWU/t that this computation provider can provide.
     */
    default int getMaxCWUt() {
        Collection<IResearchComputationProvider> list = new ArrayList<>();
        list.add(this);
        return getMaxCWUt(list);
    }

    /**
     * The maximum of CWU/t that this computation provider can provide.
     *
     * @param seen The Optical Computation Providers already checked
     */
    int getMaxCWUt(@NotNull Collection<IResearchComputationProvider> seen);

    /**
     * Whether this Computation Provider can "Bridge" with other Computation Providers.
     * Checked by machines like the Network Switch.
     */
    default boolean canBridge() {
        Collection<IResearchComputationProvider> list = new ArrayList<>();
        list.add(this);
        return canBridge(list);
    }

    /**
     * Whether this Computation Provider can "Bridge" with other Computation Providers.
     * Checked by machines like the Network Switch.
     *
     * @param seen The Optical Computation Providers already checked
     */
    boolean canBridge(@NotNull Collection<IResearchComputationProvider> seen);
}
