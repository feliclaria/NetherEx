/*
 * NetherEx
 * Copyright (c) 2016-2017 by LogicTechCorp
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

package nex.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import nex.entity.monster.EntityWight;
import nex.handler.ConfigHandler;
import nex.init.NetherExBlocks;
import nex.world.gen.feature.WorldGenGlowStone;
import nex.world.gen.feature.WorldGenMinableMeta;
import nex.world.gen.feature.WorldGenPit;

import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class BiomeArcticAbyss extends BiomeNetherEx
{
    private WorldGenerator glowstonePass1 = new WorldGenGlowStone();
    private WorldGenerator glowstonePass2 = new WorldGenGlowStone();
    private WorldGenerator quartzOre = new WorldGenMinableMeta(NetherExBlocks.ORE_QUARTZ.getStateFromMeta(1), 14, NetherExBlocks.BLOCK_NETHERRACK.getStateFromMeta(1));
    private WorldGenerator rimeOre = new WorldGenMinableMeta(NetherExBlocks.ORE_RIME.getDefaultState(), 7, NetherExBlocks.BLOCK_NETHERRACK.getStateFromMeta(1));
    private WorldGenerator ichorPit = new WorldGenPit(NetherExBlocks.FLUID_ICHOR, Blocks.PACKED_ICE.getDefaultState(), Blocks.MAGMA.getDefaultState());

    public BiomeArcticAbyss()
    {
        super(new BiomeProperties("Arctic Abyss").setTemperature(0.0F).setRainfall(0.0F).setRainDisabled(), "arctic_abyss", ConfigHandler.BiomeArcticAbyss.biomeRarity, new ItemStack(Blocks.MAGMA));

        spawnableMonsterList.add(new SpawnListEntry(EntityWight.class, 100, 1, 4));

        topBlock = Blocks.PACKED_ICE.getDefaultState();
        fillerBlock = NetherExBlocks.BLOCK_NETHERRACK.getStateFromMeta(1);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, pos));

        if(ConfigHandler.BiomeArcticAbyss.generateGlowstonePass1)
        {
            for(int i = 0; i < rand.nextInt(ConfigHandler.BiomeArcticAbyss.glowstonePass1Rarity); i++)
            {
                glowstonePass1.generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(96) + 32, rand.nextInt(16) + 8));
            }
        }

        if(ConfigHandler.BiomeArcticAbyss.generateGlowstonePass2)
        {
            for(int i = 0; i < ConfigHandler.BiomeArcticAbyss.glowstonePass2Rarity; i++)
            {
                glowstonePass2.generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(96) + 32, rand.nextInt(16) + 8));
            }
        }

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, rand, pos));

        if(ConfigHandler.BiomeArcticAbyss.generateQuartzOre)
        {
            for(int i = 0; i < ConfigHandler.BiomeArcticAbyss.quartzOreRarity; i++)
            {
                quartzOre.generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(120) + 8, rand.nextInt(16)));
            }
        }

        if(ConfigHandler.BiomeArcticAbyss.generateRimeOre)
        {
            for(int i = 0; i < ConfigHandler.BiomeArcticAbyss.rimeOreRarity; i++)
            {
                rimeOre.generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(120) + 8, rand.nextInt(16)));
            }
        }

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, rand, pos));

        if(ConfigHandler.BiomeArcticAbyss.generateIchor)
        {
            if(rand.nextInt(ConfigHandler.BiomeArcticAbyss.ichorPitRarity) == 0)
            {
                ichorPit.generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(96) + 32, rand.nextInt(16) + 8));
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, pos));
    }
}