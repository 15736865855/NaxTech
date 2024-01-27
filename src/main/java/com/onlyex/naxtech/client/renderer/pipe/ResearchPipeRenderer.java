package com.onlyex.naxtech.client.renderer.pipe;

import com.onlyex.naxtech.api.utils.NTUtils;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeType;
import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityResearchPipe;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.common.ConfigHolder;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

import codechicken.lib.vec.uv.IconTransformation;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

public final class ResearchPipeRenderer extends PipeRenderer {

    public static final ResearchPipeRenderer INSTANCE = new ResearchPipeRenderer();
    private final EnumMap<ResearchPipeType, TextureAtlasSprite> pipeTextures = new EnumMap<>(ResearchPipeType.class);

    private ResearchPipeRenderer() {
        super("gt_research_pipe", NTUtils.naxId("research_pipe"));
    }

    @Override
    public void registerIcons(TextureMap map) {
        pipeTextures.put(ResearchPipeType.NORMAL, NTTextures.RESEARCH_PIPE_IN);
    }

    @Override
    public void buildRenderer(PipeRenderContext renderContext, BlockPipe<?, ?, ?> blockPipe,
                              @Nullable IPipeTile<?, ?> pipeTile, IPipeType<?> pipeType, @Nullable Material material) {
        if (pipeType instanceof ResearchPipeType) {
            renderContext.addOpenFaceRender(new IconTransformation(pipeTextures.get(pipeType)))
                    .addSideRender(false, new IconTransformation(NTTextures.RESEARCH_PIPE_SIDE));

            if (ConfigHolder.client.preventAnimatedCables) {
                renderContext.addSideRender(new IconTransformation(NTTextures.RESEARCH_PIPE_SIDE_OVERLAY));
            } else if (pipeTile instanceof TileEntityResearchPipe researchPipe && researchPipe.isActive()) {
                renderContext.addSideRender(new IconTransformation(NTTextures.RESEARCH_PIPE_SIDE_OVERLAY_ACTIVE));
            } else {
                renderContext.addSideRender(new IconTransformation(NTTextures.RESEARCH_PIPE_SIDE_OVERLAY));
            }
        }
    }

    @Override
    public TextureAtlasSprite getParticleTexture(IPipeType<?> pipeType, @Nullable Material material) {
        return NTTextures.RESEARCH_PIPE_SIDE;
    }
}
