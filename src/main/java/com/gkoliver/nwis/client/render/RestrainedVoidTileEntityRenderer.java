package com.gkoliver.nwis.client.render;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.gkoliver.nwis.common.tile.RestrainedVoidTileEntity;
import com.gkoliver.nwis.common.tile.VoidTileEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.EndPortalTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;

public class RestrainedVoidTileEntityRenderer extends EndPortalTileEntityRenderer<RestrainedVoidTileEntity> {
	private static final Random RANDOM = new Random(31100L);
	private static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((p_228882_0_) -> {
	      return RenderType.getEndPortal(p_228882_0_ + 1);
	   }).collect(ImmutableList.toImmutableList());
	public RestrainedVoidTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	public void render(RestrainedVoidTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
	      RANDOM.setSeed(31100L);
	      double d0 = tileEntityIn.getPos().distanceSq(this.renderDispatcher.renderInfo.getProjectedView(), true);
	      int i = this.getPasses(d0);
	      float f = this.getOffset();
	      Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
	      this.renderCube(tileEntityIn, f, 0.15F, matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(0)));

	      for(int j = 1; j < i; ++j) {
	         this.renderCube(tileEntityIn, f, 2.0F / (float)(18 - j), matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(j)));
	      }

	   }

	   private void renderCube(RestrainedVoidTileEntity tileEntityIn, float p_228883_2_, float p_228883_3_, Matrix4f p_228883_4_, IVertexBuilder p_228883_5_) {
	      float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
	      float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
	      float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, p_228883_2_, 1.0F, 1.0F, 1.0F, 1.0F, f, f1, f2, Direction.SOUTH);
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 1.0F, p_228883_2_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f, f1, f2, Direction.NORTH);
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 1.0F, 1.0F, p_228883_2_, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.EAST);
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.0F, 0.0F, p_228883_2_, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.WEST);
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f, f1, f2, Direction.DOWN);
	      this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 1.0F, p_228883_2_, p_228883_2_, 1.0F, 1.0F, 0.0F, 0.0F, f, f1, f2, Direction.UP);
	   }
	   
	   private void renderFace(RestrainedVoidTileEntity tileEntityIn, Matrix4f p_228884_2_, IVertexBuilder p_228884_3_, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float p_228884_12_, float p_228884_13_, float p_228884_14_, Direction p_228884_15_) {
		      if (tileEntityIn.shouldRenderFace(p_228884_15_)) {
		         p_228884_3_.pos(p_228884_2_, p_228884_4_, p_228884_6_, p_228884_8_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
		         p_228884_3_.pos(p_228884_2_, p_228884_5_, p_228884_6_, p_228884_9_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
		         p_228884_3_.pos(p_228884_2_, p_228884_5_, p_228884_7_, p_228884_10_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
		         p_228884_3_.pos(p_228884_2_, p_228884_4_, p_228884_7_, p_228884_11_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
		      }

		   }
	@Override
	protected int getPasses(double p_191286_1_) {
	      return super.getPasses(p_191286_1_) + 1;
	}
	
	protected float getOffset() {
	      return 0.75F;
	}
	

}
