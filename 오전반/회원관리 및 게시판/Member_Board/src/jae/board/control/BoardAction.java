package jae.board.control;

import java.io.BufferedReader;
import java.io.IOException;

public interface BoardAction {

	public void execute(BufferedReader bufferedReader) throws IOException;

}
