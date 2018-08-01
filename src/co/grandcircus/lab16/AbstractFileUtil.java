package co.grandcircus.lab16;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Create a concrete subclass that knows how to convert between objects and
 * lines in the file.
 *
 * @param <T>
 *            The type of the objects written to the file
 */
public abstract class AbstractFileUtil<T> {

	// The path to the file to use
	protected String fileName;

	// Construct with a file path
	public AbstractFileUtil(String fileName) {
		this.fileName = fileName;
	}

	// Modify this method as necessary to convert a line of text from the file to a
	// new item instance
	abstract protected T convertLineToItem(String line);

	// Modify this method as necessary to convert an item instance to a line of text
	// in the file
	abstract protected String convertItemToLine(T item);

	public List<T> readFile() {
		List<T> items = new ArrayList<>();

		try (
				// Open/prepare the resources in the try resources block
				FileInputStream fileInputStream = new FileInputStream(fileName);
				Scanner fileScanner = new Scanner(fileInputStream)) {
			// loop until the end of the file
			while (fileScanner.hasNextLine()) {
				// read each line as a string
				String line = fileScanner.nextLine();
				// then convert it to an object
				items.add(convertLineToItem(line));
			}

		} catch (FileNotFoundException ex) {
			// If the file doesn't exist, there just aren't any items.
			return items;
		} catch (IOException e) {
			// If something else crazy goes wrong, print out the error.
			System.err.println("Something unexpected happended.");
			e.printStackTrace();
		}

		// Finally return the array of items.
		return items;
	}

	public void appendLine(T item) {
		// convert player object to a string line of text to be written to the file
		String line = convertItemToLine(item);

		try (
				// The `true` here tells the FileOutputStream to append to the file rather than
				// overwriting it
				FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);) {
			// write to the file
			fileWriter.println(line);

		} catch (IOException e) {
			// If something else crazy goes wrong, print out the error.
			System.err.println("Something unexpected happended.");
			e.printStackTrace();
		}
	}

	public void deleteItem(T itemToDelete) {
		// convert player object to a string line of text to be written to the file
		String lineToDelete = convertItemToLine(itemToDelete);
		List<T> oldItems = readFile();

		try (
				// For Writing
				FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);

		) {
			// Write all items to the file, except the item to be deleted
			for (T item : oldItems) {
				String line = convertItemToLine(item);
				if (!line.equals(lineToDelete)) {
					fileWriter.println(line);
				}
			}

		} catch (IOException e) {
			// If something else crazy goes wrong, print out the error.
			System.err.println("Something unexpected happended.");
			e.printStackTrace();
		}
	}

	public void writeFile(List<T> items) {
		try (
				// The `false` here tells the FileOutputStream to overwrite the file, rather
				// than append to it
				FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);) {
			// write to the file
			for (T item : items) {
				// each item must be converted to a string of text to write to the file
				String line = convertItemToLine(item);
				fileWriter.println(line);
			}

		} catch (IOException e) {
			// If something else crazy goes wrong, print out the error.
			System.err.println("Something unexpected happended.");
			e.printStackTrace();
		}
	}

}
