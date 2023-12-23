package com.onlyex.naxtech.mixin.gregtech;

import com.onlyex.naxtech.api.capability.pollution.PollutionMapManager;
import com.onlyex.naxtech.api.capability.pollution.PollutionProvider;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(value = MTETrait.class, remap = false)
public abstract class MixinWorkableTieredMetaTileEntity {

    @Final
    @Shadow
    protected MetaTileEntity metaTileEntity;

    @Inject(
            method = "update()V",
            at = @At(
                    value = "HEAD"
            )
    )
    public void update(CallbackInfo ci) {
        if (metaTileEntity instanceof WorkableTieredMetaTileEntity metaTileEntity) {
            int pollution = PollutionMapManager.getPollutionByMap(metaTileEntity.getRecipeMap());
            Chunk chunk = metaTileEntity.getWorld().getChunk(metaTileEntity.getPos());
            if (chunk.hasCapability(PollutionProvider.pollution, null)) {
                Objects.requireNonNull(chunk.getCapability(PollutionProvider.pollution, null)).addPollution(pollution);
            }
        }
    }

}
