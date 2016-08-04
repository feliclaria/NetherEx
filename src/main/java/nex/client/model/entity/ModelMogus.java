package nex.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMogus extends ModelBase
{
    private ModelRenderer cap;
    private ModelRenderer topCap;
    private ModelRenderer body;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;

    public ModelMogus()
    {
        textureWidth = 64;
        textureHeight = 32;

        cap = new ModelRenderer(this, 0, 0);
        cap.addBox(-2F, 0F, -2F, 5, 1, 5);
        cap.setRotationPoint(-1F, 18F, 0F);
        cap.setTextureSize(64, 32);
        cap.mirror = true;
        setRotation(cap, 0F, 0F, 0F);
        topCap = new ModelRenderer(this, 20, 0);
        topCap.addBox(-1F, -1F, -1F, 3, 1, 3);
        topCap.setRotationPoint(-1F, 18F, 0F);
        topCap.setTextureSize(64, 32);
        topCap.mirror = true;
        setRotation(topCap, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 6);
        body.addBox(-2F, 1F, -1F, 3, 4, 3);
        body.setRotationPoint(0F, 18F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightLeg = new ModelRenderer(this, 12, 11);
        rightLeg.addBox(-1F, 0F, 0F, 1, 1, 1);
        rightLeg.setRotationPoint(-1F, 23F, 0F);
        rightLeg.setTextureSize(64, 32);
        rightLeg.mirror = true;
        setRotation(rightLeg, 0F, 0F, 0F);
        leftLeg = new ModelRenderer(this, 12, 11);
        leftLeg.addBox(0F, 0F, 0F, 1, 1, 1);
        leftLeg.setRotationPoint(0F, 23F, 0F);
        leftLeg.setTextureSize(64, 32);
        leftLeg.mirror = true;
        setRotation(leftLeg, 0F, 0F, 0F);
        rightArm = new ModelRenderer(this, 16, 10);
        rightArm.addBox(-1F, 0F, 0F, 1, 2, 1);
        rightArm.setRotationPoint(-2F, 20F, 0F);
        rightArm.setTextureSize(64, 32);
        rightArm.mirror = true;
        setRotation(rightArm, 0F, 0F, 0F);
        leftArm = new ModelRenderer(this, 16, 10);
        leftArm.addBox(0F, 0F, 0F, 1, 2, 1);
        leftArm.setRotationPoint(1F, 20F, 0F);
        leftArm.setTextureSize(64, 32);
        leftArm.mirror = true;
        setRotation(leftArm, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        cap.render(f5);
        topCap.render(f5);
        body.render(f5);
        leftLeg.render(f5);
        rightLeg.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    private void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}