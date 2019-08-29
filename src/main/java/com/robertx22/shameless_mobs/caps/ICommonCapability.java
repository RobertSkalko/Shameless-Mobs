package com.robertx22.shameless_mobs.caps;

import net.minecraft.nbt.CompoundNBT;

public interface ICommonCapability {

    CompoundNBT getNBT();

    void setNBT(CompoundNBT value);
}