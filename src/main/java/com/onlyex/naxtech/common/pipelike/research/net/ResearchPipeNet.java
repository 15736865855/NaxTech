package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.common.pipelike.research.ResearchPipeProperties;
import gregtech.api.pipenet.Node;
import gregtech.api.pipenet.PipeNet;
import gregtech.api.pipenet.WorldPipeNet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ResearchPipeNet extends PipeNet<ResearchPipeProperties> {

    private final Map<BlockPos, ResearchRoutePath> NET_DATA = new Object2ObjectOpenHashMap<>();

    public ResearchPipeNet(WorldPipeNet<ResearchPipeProperties, ? extends PipeNet<ResearchPipeProperties>> world) {
        super(world);
    }

    @Nullable
    public ResearchRoutePath getNetData(BlockPos pipePos, EnumFacing facing) {
        if (NET_DATA.containsKey(pipePos)) {
            return NET_DATA.get(pipePos);
        }
        ResearchRoutePath data = ResearchNetWalker.createNetData(getWorldData(), pipePos, facing);
        if (data == ResearchNetWalker.FAILED_MARKER) {
            // walker failed, don't cache, so it tries again on next insertion
            return null;
        }

        NET_DATA.put(pipePos, data);
        return data;
    }

    @Override
    public void onNeighbourUpdate(BlockPos fromPos) {
        NET_DATA.clear();
    }

    @Override
    public void onPipeConnectionsUpdate() {
        NET_DATA.clear();
    }

    @Override
    protected void transferNodeData(Map<BlockPos, Node<ResearchPipeProperties>> transferredNodes,
                                    PipeNet<ResearchPipeProperties> parentNet) {
        super.transferNodeData(transferredNodes, parentNet);
        NET_DATA.clear();
        ((ResearchPipeNet) parentNet).NET_DATA.clear();
    }

    @Override
    protected void writeNodeData(ResearchPipeProperties nodeData, NBTTagCompound tagCompound) {}

    @Override
    protected ResearchPipeProperties readNodeData(NBTTagCompound tagCompound) {
        return ResearchPipeProperties.INSTANCE;
    }
}
