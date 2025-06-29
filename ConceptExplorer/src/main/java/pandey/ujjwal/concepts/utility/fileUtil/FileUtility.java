package pandey.ujjwal.concepts.utility.fileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.Part;
import pandey.ujjwal.concepts.utility.constants.Const;

public class FileUtility {
	public static String createSaveFileAndDirectory(String filePurpose, String userId, Part inputFilePart,
			Boolean saveFile, Boolean returnDirectory) {
		Path realPath = null;
		try {
			Path relativePath = Paths.get("");
			realPath = relativePath.toRealPath(); // To the project path.
			String restPath = ("/uploadedFiles/" + filePurpose + "/" + userId).replace(" ", "");
			File fileDirectoryPath = new File(realPath + restPath);
			System.out.println(fileDirectoryPath);

			if (fileDirectoryPath.exists()) {
				System.out.println("Folder exists");
			} else if (fileDirectoryPath.mkdirs()) {
				System.out.println("Folder created");
			}

			if (saveFile == true) {
				return saveFileInDrive(inputFilePart, restPath, fileDirectoryPath.toString());
			} else {
				if (returnDirectory) {
					return fileDirectoryPath.toString();
				} else {
					return null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String saveFileInDrive(final Part inputFilePart, String newDire, String directoryPath) {
		try {
			String uploadingFile = null;
			if (inputFilePart.getSize() > 0) {
				OutputStream out = null;
				try {
					InputStream fileContent = null;
					int read = 0;
					String originalFileName = getFileName(inputFilePart).replace(" ", "");
					uploadingFile = directoryPath + File.separator + originalFileName;
					out = new FileOutputStream(new File(uploadingFile));
					fileContent = inputFilePart.getInputStream();
					final byte[] readFile = new byte[1024]; // 1MB buffer
					while ((read = fileContent.read(readFile)) != -1) {
						out.write(readFile, 0, read);
					}
					return Const.CURRENT_HOST + newDire + "/" + originalFileName;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (out != null)
						out.close();
				}
			}
			return uploadingFile;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return getCurrentDateTimeFormatted("yyyy_MM_dd_HH_mm_ss")
						.concat(content.substring(content.indexOf('=') + 1).trim().replace("\"", ""));
			}
		}
		return null;
	}

	public static String getCurrentDateTimeFormatted(String format) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return now.format(formatter);
	}
}