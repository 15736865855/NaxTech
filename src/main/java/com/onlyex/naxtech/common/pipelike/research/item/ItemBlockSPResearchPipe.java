package com.onlyex.naxtech.common.pipelike.research.item;

import com.onlyex.naxtech.common.pipelike.research.block.BlockResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeProperties;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeType;
import com.onlyex.naxtech.common.pipelike.research.block.BlockSPResearchPipe;
import gregtech.api.pipenet.block.ItemBlockPipe;
import gregtech.client.utils.TooltipHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemBlockSPResearchPipe extends ItemBlockPipe<ResearchPipeType, ResearchPipeProperties> {

    public ItemBlockSPResearchPipe(BlockSPResearchPipe block) {
        super(block);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(I18n.format("tile.sp_research_pipe_normal.tooltip1"));

        if (TooltipHelper.isShiftDown()) {
            tooltip.add(I18n.format("gregtech.tool_action.wire_cutter.connect"));
        } else {
            tooltip.add(I18n.format("gregtech.tool_action.show_tooltips"));
        }
    }
}
