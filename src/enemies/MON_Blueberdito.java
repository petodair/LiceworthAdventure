package enemies;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BShot;
import object.OBJ_Casca_Blueberdito;
import object.OBJ_Golden_Coin;

public class MON_Blueberdito extends Entity{
	
	GamePanel gp;

	public MON_Blueberdito(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Blueberdito";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 3;
		projectile = new OBJ_BShot(gp);
		
		solidArea.x = 12;
		solidArea.y = 12;
		solidArea.width = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		up1 = setup("/monsters/blueberdito_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/blueberdito_up2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/blueberdito_down1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/blueberdito_down2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/blueberdito_left1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/blueberdito_left2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/blueberdito_right1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/blueberdito_right2", gp.tileSize, gp.tileSize);
		
	}
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; //PEGAR UM NUMERO DE 1 A 100
			if(i <= 25) {
				direction ="up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
		int i = new Random().nextInt(100)+1;
		if(i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
			
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	public void checkDrop() {
		
		int i = new Random().nextInt(100)+1;
		
		//DEFINIR O DROP DO MONSTRO
		if(i < 50) {
			dropItem(new OBJ_Golden_Coin(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Casca_Blueberdito(gp));
		}
		if(i >= 75 && i < 100) {
			
		}
	}

}
