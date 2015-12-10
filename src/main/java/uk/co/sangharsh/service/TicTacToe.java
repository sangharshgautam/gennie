package uk.co.sangharsh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.telegram.client.pojo.User;
import org.telegram.client.type.Command;

public class TicTacToe extends TwinPlayerGame{

	private String[][] matrix;
	
	public TicTacToe(User playerOne) {
		super(playerOne);
		this.matrix  = new String[3][3];
	}

	public List<List<String>> keyboardX() {
		return keyboard("X");
	}
	public List<List<String>> keyboardO() {
		return keyboard("O");
	}

	public List<List<String>> keyboard(String p) {
		List<List<String>> keyboard = new ArrayList<List<String>>();
		for(int i=0;i<matrix.length ; i++){
			String[] data = matrix[i];
			List<String> row = new ArrayList<String>();
			for(int j = 1; j<data.length;j++){
				String button = data[j];
				if(StringUtils.isBlank(button)){
					row.add(p+((3*i)+j));
				}else{
					row.add(" ");
				}
			}
			keyboard.add(row);
		}
		keyboard.add(new ArrayList<String>(){{
			add(Command.QUIT.toString());
		}});
		return keyboard;
	}

	public void move(String string) {
		if(StringUtils.length(string) == 2){
			String player = string.substring(0, 1);
			int block = Integer.parseInt(string.substring(1, 2));
			switch (block) {
			case 1:
				this.matrix[0][0] = player;
				break;
			case 2:
				this.matrix[0][1] = player;
				break;
			case 3:
				this.matrix[0][2] = player;
				break;
			case 4:
				this.matrix[1][0] = player;
				break;
			case 5:
				this.matrix[1][1] = player;
				break;
			case 6:
				this.matrix[1][2] = player;
				break;
			case 7:
				this.matrix[2][0] = player;
				break;
			case 8:
				this.matrix[2][1] = player;
				break;
			case 9:
				this.matrix[2][2] = player;
			default:
				break;
			}
				
		}
	}
}
