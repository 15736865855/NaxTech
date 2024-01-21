package com.onlyex.naxtech.api.metatileentity.multiblock;

import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class NTMultiblockDisplayText {

    public static class Builder {
        private final List<ITextComponent> textList;
        private final boolean isStructureFormed;
        private boolean isActive;

        private Builder(List<ITextComponent> textList, boolean isStructureFormed) {
            this.textList = textList;
            this.isStructureFormed = isStructureFormed;
        }

        /**普通-1
         *
         */
        public Builder addResearchnUsageLine(int maxRWUt) {
            if (!isStructureFormed) return this;
            if (maxRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxRWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addResearchnUsageExactLine(int currentRWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentRWUt) + " RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.research.usage",
                        Researchn));
            }
            return this;
        }

        /**生物-gooware-2
         *
         */
        public Builder addGoowareResearchnUsageLine(int maxGORWUt) {
            if (!isStructureFormed) return this;
            if (maxGORWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxGORWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.gooware.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addGoowareResearchnUsageExactLine(int currentGORWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentGORWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentGORWUt) + " GO-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.gooware.research.usage",
                        Researchn));
            }
            return this;
        }

        /**光学-optical-3
         *
         */
        public Builder addOpticalResearchnUsageLine(int maxOPRWUt) {
            if (!isStructureFormed) return this;
            if (maxOPRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxOPRWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.optical.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addOpticalResearchnUsageExactLine(int currentOPRWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentOPRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentOPRWUt) + " OP-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.optical.research.usage",
                        Researchn));
            }
            return this;
        }

        /**自旋电子学-spintronic-4
         *
         */
        public Builder addSpintronicResearchnUsageLine(int maxSPRWUt) {
            if (!isStructureFormed) return this;
            if (maxSPRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxSPRWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.spintronic.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addSpintronicResearchnUsageExactLine(int currentSPRWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentSPRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentSPRWUt) + " SP-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.spintronic.research.usage",
                        Researchn));
            }
            return this;
        }

        /**寰宇-cosmic-5
         *
         */
        public Builder addCosmicResearchnUsageLine(int maxCORWUt) {
            if (!isStructureFormed) return this;
            if (maxCORWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxCORWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.cosmic.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addCosmicResearchnUsageExactLine(int currentCORWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentCORWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentCORWUt) + " CO-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.cosmic.research.usage",
                        Researchn));
            }
            return this;
        }

        /**超因果-supra_causal-6
         *
         */
        public Builder addSupraCausalResearchnUsageLine(int maxSCARWUt) {
            if (!isStructureFormed) return this;
            if (maxSCARWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxSCARWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_causal.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addSupraCausalResearchnUsageExactLine(int currentSCARWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentSCARWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentSCARWUt) + " SCA-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_causal.research.usage",
                        Researchn));
            }
            return this;
        }

        /**超时空-supra_chronal-7
         *
         */
        public Builder addSupraChronalResearchnUsageLine(int maxSCHRWUt) {
            if (!isStructureFormed) return this;
            if (maxSCHRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxSCHRWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_chronal.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addSupraChronalResearchnUsageExactLine(int currentSCHRWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentSCHRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentSCHRWUt) + " SCH-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_chronal.research.usage",
                        Researchn));
            }
            return this;
        }

        /**超维度-supra_dimension-8
         *
         */
        public Builder addSupraDimensionResearchnUsageLine(int maxSDIRWUt) {
            if (!isStructureFormed) return this;
            if (maxSDIRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(maxSDIRWUt));
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_dimension.research.max",
                        Researchn));
            }
            return this;
        }
        public Builder addSupraDimensionResearchnUsageExactLine(int currentSDIRWUt) {
            if (!isStructureFormed) return this;
            if (isActive && currentSDIRWUt > 0) {
                ITextComponent Researchn = TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                        TextFormattingUtil.formatNumbers(currentSDIRWUt) + " SDI-RWU/t");
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "naxtech.multiblock.supra_dimension.research.usage",
                        Researchn));
            }
            return this;
        }
    }
}
