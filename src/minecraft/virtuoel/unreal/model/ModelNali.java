package virtuoel.unreal.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNali extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarmtop;
    ModelRenderer leftarmtop;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarmbottom;
    ModelRenderer rightarmbottom;
  
  public ModelNali()
  {
    textureWidth = 64;
    textureHeight = 40;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 12, 8);
      head.setRotationPoint(0F, -8F, 0F);
      head.setTextureSize(64, 40);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 20);
      body.addBox(-4F, -8F, -2F, 8, 16, 4);
      body.setRotationPoint(0F, 4F, 0F);
      body.setTextureSize(64, 40);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarmtop = new ModelRenderer(this, 40, 20);
      rightarmtop.addBox(-4F, -2F, -2F, 4, 16, 4);
      rightarmtop.setRotationPoint(-4F, -2F, 0F);
      rightarmtop.setTextureSize(64, 40);
      rightarmtop.mirror = true;
      setRotation(rightarmtop, -1.570796F, 0F, 0F);
      leftarmtop = new ModelRenderer(this, 40, 20);//XXX fixd
      leftarmtop.mirror = true;
      leftarmtop.addBox(0F, -2F, -2F, 4, 16, 4);
      leftarmtop.setRotationPoint(4F, -2F, 0F);
      leftarmtop.setTextureSize(64, 40);
      leftarmtop.mirror = true;
      setRotation(leftarmtop, -1.570796F, 0F, 0F);
      leftarmtop.mirror = false;
      rightleg = new ModelRenderer(this, 0, 20);//XXX fixd
      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 40);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 20);//XXX fixd
      leftleg.mirror = true;
      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      leftleg.setRotationPoint(2F, 12F, 0F);
      leftleg.setTextureSize(64, 40);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      leftleg.mirror = false;
      leftarmbottom = new ModelRenderer(this, 40, 20);
      leftarmbottom.addBox(-5F, -2F, -2F, 4, 16, 4);
      leftarmbottom.setRotationPoint(-3F, 4F, 0F);
      leftarmbottom.setTextureSize(64, 40);
      leftarmbottom.mirror = true;
      setRotation(leftarmbottom, -1.570796F, 0F, 0F);
      rightarmbottom = new ModelRenderer(this, 40, 20);//XXX fixd
      rightarmbottom.mirror = true;
      rightarmbottom.addBox(0F, -2F, -2F, 4, 16, 4);
      rightarmbottom.setRotationPoint(4F, 4F, 0F);
      rightarmbottom.setTextureSize(64, 40);
      rightarmbottom.mirror = true;
      setRotation(rightarmbottom, -1.570796F, 0F, 0F);
      rightarmbottom.mirror = false;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarmtop.render(f5);
    leftarmtop.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    leftarmbottom.render(f5);
    rightarmbottom.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
