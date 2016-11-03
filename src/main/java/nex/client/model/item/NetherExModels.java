/*
 * Copyright (C) 2016.  LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nex.client.model.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import nex.NetherEx;
import nex.block.BlockNetherExStone;
import nex.block.BlockVanillaStone;
import nex.init.NetherExBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(Side.CLIENT)
public class NetherExModels
{
    private static final Logger LOGGER = LogManager.getLogger("NetherEx|NetherExModels");

    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomStateMapper(NetherExBlocks.VANILLA_STONE_SLAB, new StateMap.Builder().ignore(NetherExBlocks.VANILLA_STONE_SLAB.HALF).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.VANILLA_STONE_SLAB_DOUBLE, new StateMap.Builder().ignore(NetherExBlocks.VANILLA_STONE_SLAB_DOUBLE.HALF).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.VANILLA_STONE_WALL, new StateMap.Builder().ignore(NetherExBlocks.VANILLA_STONE_WALL.VARIANT).build());

        for(BlockVanillaStone.EnumTypeWithOut type : BlockVanillaStone.EnumTypeWithOut.values())
        {
            registerModel(NetherExBlocks.VANILLA_STONE_SLAB, type.ordinal(), NetherExBlocks.VANILLA_STONE_SLAB.getRegistryName().toString(), String.format("type=%s", type.getName()));
            registerModel(NetherExBlocks.VANILLA_STONE_SLAB_DOUBLE, type.ordinal(), NetherExBlocks.VANILLA_STONE_SLAB_DOUBLE.getRegistryName().toString(), String.format("type=%s", type.getName()));
            registerModel(NetherExBlocks.VANILLA_STONE_FENCE, type.ordinal(), NetherExBlocks.VANILLA_STONE_FENCE.getRegistryName().toString(), String.format("east=false,north=true,south=true,type=%s,west=false", type.getName()));
        }

        for(BlockVanillaStone.EnumTypeWith type : BlockVanillaStone.EnumTypeWith.values())
        {
            registerModel(NetherExBlocks.VANILLA_STONE_WALL, type.ordinal(), NetherExBlocks.VANILLA_STONE_WALL.getRegistryName().toString(), String.format("east=false,north=true,south=true,type=%s,up=true,west=false", type.getName()));
        }

        registerModel(NetherExBlocks.VANILLA_NETHER_BRICK_RED_STAIRS, "normal");

        ModelLoader.setCustomStateMapper(NetherExBlocks.STONE_SLAB, new StateMap.Builder().ignore(NetherExBlocks.STONE_SLAB.HALF).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.STONE_SLAB_DOUBLE, new StateMap.Builder().ignore(NetherExBlocks.STONE_SLAB.HALF).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.STONE_WALL, new StateMap.Builder().ignore(NetherExBlocks.STONE_WALL.VARIANT).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.BASALT_FENCE_GATE, new StateMap.Builder().ignore(NetherExBlocks.BASALT_FENCE_GATE.POWERED).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.SMOOTH_BASALT_FENCE_GATE, new StateMap.Builder().ignore(NetherExBlocks.SMOOTH_BASALT_FENCE_GATE.POWERED).build());
        ModelLoader.setCustomStateMapper(NetherExBlocks.BASALT_BRICK_FENCE_GATE, new StateMap.Builder().ignore(NetherExBlocks.BASALT_BRICK_FENCE_GATE.POWERED).build());

        for(BlockNetherExStone.EnumType type : BlockNetherExStone.EnumType.values())
        {
            registerModel(NetherExBlocks.STONE, type.ordinal(), NetherExBlocks.STONE.getRegistryName().toString(), String.format("type=%s", type.getName()));
            registerModel(NetherExBlocks.STONE_SLAB, type.ordinal(), NetherExBlocks.STONE_SLAB.getRegistryName().toString(), String.format("type=%s", type.getName()));
            registerModel(NetherExBlocks.STONE_SLAB_DOUBLE, type.ordinal(), NetherExBlocks.STONE_SLAB_DOUBLE.getRegistryName().toString(), String.format("type=%s", type.getName()));
            registerModel(NetherExBlocks.STONE_WALL, type.ordinal(), NetherExBlocks.STONE_WALL.getRegistryName().toString(), String.format("east=false,north=true,south=true,type=%s,up=true,west=false", type.getName()));
            registerModel(NetherExBlocks.STONE_FENCE, type.ordinal(), NetherExBlocks.STONE_FENCE.getRegistryName().toString(), String.format("east=false,north=true,south=true,type=%s,west=false", type.getName()));
        }

        registerModel(NetherExBlocks.BASALT_STAIRS, "normal");
        registerModel(NetherExBlocks.SMOOTH_BASALT_STAIRS, "normal");
        registerModel(NetherExBlocks.BASALT_BRICK_STAIRS, "normal");
        registerModel(NetherExBlocks.BASALT_FENCE_GATE, "normal");
        registerModel(NetherExBlocks.SMOOTH_BASALT_FENCE_GATE, "normal");
        registerModel(NetherExBlocks.BASALT_BRICK_FENCE_GATE, "normal");

        LOGGER.info("Model registration has been completed.");
    }

    private static void registerModel(IFluidBlock block)
    {
        Item item = Item.getItemFromBlock((Block) block);
        ModelBakery.registerItemVariants(item);

        ModelResourceLocation modelLocation = new ModelResourceLocation(NetherEx.MOD_ID + ":fluid", block.getFluid().getName());

        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelLocation));

        ModelLoader.setCustomStateMapper((Block) block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return modelLocation;
            }
        });
    }

    private static void registerModel(Object object, String location)
    {
        if(object instanceof Block)
        {
            ModelResourceLocation modelLocation = new ModelResourceLocation(((Block) object).getRegistryName(), location);
            ModelBakery.registerItemVariants((Item.getItemFromBlock((Block) object)), modelLocation);
            registerModel(object, MeshDefinitionFix.create(stack -> modelLocation));
        }
        else if(object instanceof Item)
        {
            ModelResourceLocation modelLocation = new ModelResourceLocation(((Item) object).getRegistryName(), location);
            ModelBakery.registerItemVariants(((Item) object), modelLocation);
            registerModel(object, MeshDefinitionFix.create(stack -> modelLocation));
        }
    }

    private static void registerModel(Object object, int metadata, String location, String variant)
    {
        if(object instanceof Block)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) object), metadata, new ModelResourceLocation(location, variant));
        }
        else if(object instanceof Item)
        {
            ModelLoader.setCustomModelResourceLocation((Item) object, metadata, new ModelResourceLocation(location, variant));
        }
    }

    private static void registerModel(Object object, ItemMeshDefinition definition)
    {
        if(object instanceof Block)
        {
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock((Block) object), definition);
        }
        else if(object instanceof Item)
        {
            ModelLoader.setCustomMeshDefinition((Item) object, definition);
        }
    }
}