package com.onlyex.naxtech.client.shader;

import com.onlyex.naxtech.client.event.NTClientEventHandler;
import com.onlyex.naxtech.client.utils.ShaderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;

public class CosmicShaderHelper {
    public static final ShaderCallback shaderCallback;

    public static float[] lightlevel = new float[3];

    public static boolean inventoryRender = false;
    public static float cosmicOpacity = 1.0f;

    static {
        shaderCallback = new ShaderCallback() {
            @Override
            public void call(int shader) {
                Minecraft mc = Minecraft.getMinecraft();

                float yaw = 0;
                float pitch = 0;
                float scale = 1.0f;

                if (!inventoryRender) {
                    yaw = (float) ((mc.player.rotationYaw * 2 * Math.PI) / 360.0);
                    pitch = -(float) ((mc.player.rotationPitch * 2 * Math.PI) / 360.0);
                } else {
                    scale = 25.0f;
                }

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                ARBShaderObjects.glUniform1fARB(x, yaw);

                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                ARBShaderObjects.glUniform1fARB(z, pitch);

                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);

                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);

                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, NTClientEventHandler.cosmicUVs);

                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                ARBShaderObjects.glUniform1fARB(s, scale);

                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
            }
        };
    }

    public static void useShader() {
        ShaderHelper.useShader(ShaderHelper.cosmicShader, shaderCallback);
    }

    public static void releaseShader() {
        ShaderHelper.releaseShader();
    }

    public static void setLightFromLocation(World world, BlockPos pos) {
        // 检查世界对象是否为空
        if (world == null) {
            setLightLevel(1.0f);
            return;
        }

        // 获取指定位置的光照亮度
        int lightValue = world.getLight(pos);

        // 检查是否能够获取光照亮度
        if (lightValue == 0) {
            setLightLevel(1.0f);
            return;
        }

        // 将光照亮度转换为 RGB 值
        float r = ((lightValue >> 16) & 0xFF) / 255.0f;
        float g = ((lightValue >> 8) & 0xFF) / 255.0f;
        float b = (lightValue & 0xFF) / 255.0f;

        // 设置光照强度
        setLightLevel(r, g, b);
    }

    public static void setLightLevel(float level) {
        setLightLevel(level, level, level);
    }

    public static void setLightLevel(float r, float g, float b) {
        lightlevel[0] = Math.max(0.0f, Math.min(1.0f, r));
        lightlevel[1] = Math.max(0.0f, Math.min(1.0f, g));
        lightlevel[2] = Math.max(0.0f, Math.min(1.0f, b));
    }
}
