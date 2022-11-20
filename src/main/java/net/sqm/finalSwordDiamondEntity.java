package net.sqm;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class finalSwordDiamondEntity extends AbstractArrow implements ItemSupplier {
    private static double damage;
    public finalSwordDiamondEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntityInit.finalsworddiamond.get(), world);
    }

    public finalSwordDiamondEntity(EntityType<? extends finalSwordDiamondEntity> type, Level world) {
        super(type, world);
    }

    public finalSwordDiamondEntity(EntityType<? extends finalSwordDiamondEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public finalSwordDiamondEntity(EntityType<? extends finalSwordDiamondEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void playerTouch(Player entity) {
        super.playerTouch(entity);
        if (entity != this.getOwner()) {
            hitEffect();
            hitDamage();
        }

    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() != this.getOwner()) {
            hitEffect();
            hitDamage();
        }

    }

    @Override
    public void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        hitEffect();
        hitDamage();
    }

    private void hitEffect() {
        if (this.level instanceof ServerLevel _level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
            entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(this.getX(), this.getY(), this.getZ())));
            entityToSpawn.setVisualOnly(true);
            _level.addFreshEntity(entityToSpawn);
        }
        if (this.level instanceof ServerLevel _level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
            entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(this.getX(), this.getY(), this.getZ())));
            entityToSpawn.setVisualOnly(true);
            _level.addFreshEntity(entityToSpawn);
        }
        if (this.level instanceof ServerLevel _level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
            entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(this.getX(), this.getY(), this.getZ())));
            entityToSpawn.setVisualOnly(true);
            _level.addFreshEntity(entityToSpawn);
        }

        if (this.level instanceof ServerLevel _level)
            _level.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(),  25, 1.7, 1.7, 1.7, 0.6);
    }
    private void hitDamage() {
        final Vec3 _center = new Vec3(this.getX(), this.getY(), this.getZ());
        List<Entity> _entfound = this.level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (Entity entityiterator : _entfound) {
            if (entityiterator != this.getOwner() && !(entityiterator instanceof ItemEntity) && !(entityiterator instanceof ExperienceOrb)) {
                entityiterator.hurt(DamageSource.GENERIC, (float) damage);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (Math.abs(this.getDeltaMovement().x()) <= 0.34 &&
            Math.abs(this.getDeltaMovement().y()) <= 0.34 &&
            Math.abs(this.getDeltaMovement().z()) <= 0.34) {
            this.discard();
        }

            if (this.inGround)
                this.discard();
    }

    public static finalSwordDiamondEntity shoot(Level world, LivingEntity entity, double damage) {
        finalSwordDiamondEntity.damage = damage;

        finalSwordDiamondEntity diamond = new finalSwordDiamondEntity(EntityInit.finalsworddiamond.get(), entity, world);
        diamond.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, (float) 3.2, 0);
        diamond.setSilent(true);
        diamond.setCritArrow(false);
        diamond.setBaseDamage(0);
        diamond.setKnockback(0);
        diamond.setNoGravity(true);

        world.addFreshEntity(diamond);
        return diamond;
    }
}
