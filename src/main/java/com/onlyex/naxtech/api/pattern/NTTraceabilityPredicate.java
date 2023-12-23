package com.onlyex.naxtech.api.pattern;

import com.onlyex.naxtech.api.block.impl.WrappedIntTier;
import com.onlyex.naxtech.api.pattern.predicates.TierTraceabilityPredicate;
import com.onlyex.naxtech.api.utils.NTUniverUtil;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Supplier;

import static com.onlyex.naxtech.api.NTAPI.*;


public class NTTraceabilityPredicate {

    //  Tier Traceability Predicates
    public static Supplier<TierTraceabilityPredicate> MACHINE_CASINGS = () -> new TierTraceabilityPredicate(MAP_MACHINE_CASING,"MachineCasingType",null);
    public static Supplier<TierTraceabilityPredicate> CP_CASING = () -> new TierTraceabilityPredicate(MAP_CP_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CP_CASING.get(s)).getIntTier()),"ChemicalPlantCasing",null);
    public static Supplier<TierTraceabilityPredicate> CP_TUBE = () -> new TierTraceabilityPredicate(MAP_CP_TUBE,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CP_TUBE.get(s)).getIntTier()),"ChemicalPlantTube",null);
    public static Supplier<TierTraceabilityPredicate> CA_TIERED_CASING = () -> new TierTraceabilityPredicate(MAP_CA_TIRED_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CA_TIRED_CASING.get(s)).getIntTier()), "CATieredCasing", null);
    public static Supplier<TierTraceabilityPredicate> PA_CASING = () -> new TierTraceabilityPredicate(MAP_PA_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_PA_CASING.get(s)).getIntTier()), "PACasing", null);
    public static Supplier<TierTraceabilityPredicate> PA_INTERNAL_CASING = () -> new TierTraceabilityPredicate(MAP_PA_INTERNAL_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_PA_INTERNAL_CASING.get(s)).getIntTier()), "PAInternalCasing", null);

    public static Supplier<TierTraceabilityPredicate> QFT_MANIPULATOR = () -> new TierTraceabilityPredicate(MAP_QFT_MANIPULATOR,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_MANIPULATOR.get(s)).getIntTier()), "QFTManipulator", null);
    public static Supplier<TierTraceabilityPredicate> QFT_SHIELDING_CORE = () -> new TierTraceabilityPredicate(MAP_QFT_SHIELDING_CORE,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_SHIELDING_CORE.get(s)).getIntTier()), "QFTShieldingCore", null);
    public static Supplier<TierTraceabilityPredicate> QFT_GLASS = () -> new TierTraceabilityPredicate(MAP_QFT_GLASS,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_GLASS.get(s)).getIntTier()), "QFTGlass", null);

    //  Optional Traceability Predicates
    public static TraceabilityPredicate optionalStates(String mark, IBlockState... allowedStates) {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            if (state.getBlock() instanceof VariantActiveBlock) {
                blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
            }
            if (ArrayUtils.contains(allowedStates, state)) {
                return (blockWorldState.getMatchContext().getOrPut(mark, true));
            }
            return blockWorldState.getMatchContext().get(mark) == null;
        }, getCandidates(allowedStates));
    }

    public static TraceabilityPredicate optionalAbilities(String mark, MultiblockAbility<?>... allowedAbilities) {
        return new TraceabilityPredicate(blockWorldState -> {
            TileEntity tileEntity = blockWorldState.getTileEntity();
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof IMultiblockAbilityPart<?> && ArrayUtils.contains(allowedAbilities, ((IMultiblockAbilityPart<?>) metaTileEntity).getAbility())) {
                    Set<IMultiblockPart> partsFound = blockWorldState.getMatchContext().getOrCreate("MultiblockParts", HashSet::new);
                    partsFound.add((IMultiblockPart) metaTileEntity);
                    return (blockWorldState.getMatchContext().getOrPut(mark, true));
                }
            }
            return blockWorldState.getMatchContext().get(mark) == null;
        }, getCandidates(Arrays.stream(allowedAbilities).flatMap(ability -> MultiblockAbility.REGISTRY.get(ability).stream()).toArray(MetaTileEntity[]::new)));
    }

    //  Utils
    public static Supplier<BlockInfo[]> getCandidates(IBlockState... allowedStates) {
        return () -> Arrays.stream(allowedStates).map(state -> new BlockInfo(state, null)).toArray(BlockInfo[]::new);
    }

    public static Supplier<BlockInfo[]> getCandidates(MetaTileEntity... metaTileEntities) {
        return () -> Arrays.stream(metaTileEntities)
                .filter(Objects::nonNull)
                //.map(tile -> new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), NTUniverUtil.getTileEntity(tile)))
                .toArray(BlockInfo[]::new);
    }
}
