package com.onlyex.naxtech.common.pipelike.research.net;

import com.onlyex.naxtech.common.pipelike.research.ResearchPipeProperties;
import gregtech.api.pipenet.WorldPipeNet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class WorldResearchPipeNet extends WorldPipeNet<ResearchPipeProperties, ResearchPipeNet> {

    private static final String DATA_ID = "naxtech.research_pipe_net";

    public WorldResearchPipeNet(String name) {
        super(name);
    }

    @NotNull
    public static WorldResearchPipeNet getWorldPipeNet(@NotNull World world) {
        WorldResearchPipeNet netWorldData = (WorldResearchPipeNet) world.loadData(WorldResearchPipeNet.class, DATA_ID);
        if (netWorldData == null) {
            netWorldData = new WorldResearchPipeNet(DATA_ID);
            world.setData(DATA_ID, netWorldData);
        }
        netWorldData.setWorldAndInit(world);
        return netWorldData;
    }

    @Override
    protected ResearchPipeNet createNetInstance() {
        return new ResearchPipeNet(this);
    }
}
