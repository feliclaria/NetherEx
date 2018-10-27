package nex.block;

import lex.IModData;
import lex.block.BlockDynamic;
import lex.world.biome.BiomeBlockType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import nex.world.biome.INetherBiomeWrapper;
import nex.world.biome.NetherBiomeManager;

public abstract class BlockDynamicNetherBiome extends BlockDynamic
{
    public BlockDynamicNetherBiome(IModData data, String name, Material material, TexturePlacement texturePlacement)
    {
        super(data, name, material, texturePlacement);
    }

    @Override
    public IBlockState getDynamicState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Biome biome = world.getBiome(pos);
        INetherBiomeWrapper wrapper = NetherBiomeManager.INSTANCE.getBiomeWrapper(biome);

        if(wrapper != null)
        {
            return wrapper.getBiomeBlock(BiomeBlockType.WALL_BLOCK, null);
        }

        return Blocks.NETHERRACK.getDefaultState();
    }
}
