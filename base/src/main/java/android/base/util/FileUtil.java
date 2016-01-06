package android.base.util;

import android.annotation.TargetApi;
import android.base.constant.Constant;
import android.base.log.Log;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by clickapps on 24/7/15.
 */
public class FileUtil {

    private final static String FILE_NAME = "Sparta_";
    private final static String FILE_NAME_IMAGE = "IMG_";
    private final static String FILE_NAME_VIDEO = "VID_";
    private final static String FILE_NAME_CAMERA_IMAGE = "IMG_CAMERA_";


    private final static String TAG = FileUtil.class.getSimpleName();

    /**
     * Functionality to get directory name file
     *
     * @param context
     * @return directory file where the all the application's files are stored
     */

    public static File getDirectoryApp(Context context) {
        String cacheFilePath = Environment.getExternalStorageDirectory()
                .getPath() + "/Android/" + context.getPackageName();
        File appCacheDir = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && hasExternalStoragePermission(context)) {
            appCacheDir = new File(cacheFilePath);
        }
        if (appCacheDir == null
                || (!appCacheDir.exists() && !appCacheDir.mkdirs())) {
            appCacheDir = context.getCacheDir();
        }
        Log.d(TAG, "directory path = "
                + appCacheDir);

        return appCacheDir;
    }

    /**
     * Functionality to get directory of images cache file
     *
     * @param context
     * @return directory file where the all the application's images are stored
     */

    public static File getDirectoryAppCacheImages(Context context) {
        File dir = new File(getDirectoryApp(context),
                "/CacheImages");
        if (!dir.exists() && !dir.mkdirs()) {
            dir = context.getCacheDir();
        }
        Log.d(TAG, "image directory path = "
                + dir);

        return dir;
    }

    /**
     * Functionality to get directory of images file
     *
     * @param context
     * @return directory file where the all the application's images are stored
     */

    public static File getDirectoryAppImages(Context context) {
        File dir = new File(getDirectoryApp(context),
                "/Images");
        if (!dir.exists() && !dir.mkdirs()) {
            dir = context.getCacheDir();
        }
        Log.d(TAG, "image directory path = "
                + dir);

        return dir;
    }

    /**
     * Functionality to get directory of images file
     *
     * @param context
     * @return directory file where the all the application's images are stored
     */

    public static File getDirectoryAppVideos(Context context) {
        File dir = new File(getDirectoryApp(context),
                "/Videos");
        if (!dir.exists() && !dir.mkdirs()) {
            dir = context.getCacheDir();
        }
        Log.d(TAG, "video directory path = "
                + dir);

        return dir;
    }


    public static File getStaticFile(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            name = FILE_NAME_IMAGE + "temp.jpeg";
        } else if (!name.endsWith(".jpeg")) {
            name = name + ".jpeg";
        }
        File dir = new File(getDirectoryApp(context),
                "/cache");
        File filePath = new File(dir, name);
        return filePath;

    }

    public static File getStaticVideoFile(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            name = "temp_video.mp4";
        } else if (!name.endsWith(".mp4")) {
            name = name + ".mp4";
        }
        File dir = new File(getDirectoryApp(context),
                "/cache");
        File filePath = new File(dir, name);
        return filePath;
    }

    public static void writeStringToFile(File file, String data) {
        try {
            FileUtils.writeStringToFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStringToFile(File file, String data, boolean append) {
        try {
            FileUtils.writeStringToFile(file, data, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File src, File dst) {
        try {
            FileUtils.copyFile(src, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context
                .checkCallingOrSelfPermission(Constant.EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }


    /**
     * Functionality to search files and folders from the mobile device either
     * from internal or from external
     *
     * @param fileName file name or folder name from that all files are fetched
     * @return array list containing path of data
     * @permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
     */
    public static ArrayList<String> findFilesMain(String fileName) {
        ArrayList<String> folderNameList = new ArrayList<String>();
        ArrayList<String> fileList = new ArrayList<String>();
        File file = new File("/");
        File folderList[] = file.listFiles();
        if (folderList != null && folderList.length > 0) {
            for (int i = 0; i < folderList.length; i++) {
                File folderList1[] = folderList[i].listFiles();
                if (folderList1 != null && folderList1.length > 0) {
                    findFilesPath(folderList1, folderNameList, fileList,
                            fileName);
                } else {
                    Log.d(TAG,
                            "file name " + folderList[i].getName());
                }
            }
        }

        // // finally print folder name list
        // for (int i = 0; i < folderNameList.size(); i++) {
        // Log.d(TAG + " File Handling", "folder name "
        // + folderNameList.get(i));
        // }
        //
        // // finally print whatsapp data name list
        // for (int i = 0; i < whatsAppList.size(); i++) {
        // Log.d(TAG + " File Handling",
        // "whatsapp file name " + whatsAppList.get(i).getFileName());
        // }

        return fileList;
    }

    /**
     * Functionality to find particular folder/file from the device
     *
     * @param folderList array of files/folders
     * @param fileList   path list on which the path of searched data will also be
     *                   added
     * @param fileName   file name or folder name from that all files are fetched
     */
    private static void findFilesPath(File folderList[],
                                      ArrayList<String> folderNameList,
                                      ArrayList<String> fileList, String fileName) {
        if (folderList != null && folderList.length > 0) {
            for (int i = 0; i < folderList.length; i++) {
                File folderList1[] = folderList[i].listFiles();
                if (folderList1 != null && folderList1.length > 0) {
                    if (!folderNameList.contains(folderList[i].getName())) {
                        if (folderList[i].getName().contains(fileName)) {
                            File fileLists[] = folderList[i].listFiles();
                            if (fileList != null && fileLists.length > 0) {
                                for (int j = 0; j < fileLists.length; j++) {
                                    getFileList(fileLists, fileList);

                                }

                            }
                        } else {
                            folderNameList.add(folderList[i].getName());
                            findFilesPath(folderList1, folderNameList,
                                    fileList, fileName);
                        }
                    }
                } else {
                    Log.d(TAG + " File Handling",
                            "file name " + folderList[i].getName());
                }
            }
        }
    }

    /**
     * @param folderList array of files/folders
     * @param list       path list on which the path of searched data will also be
     *                   added
     */
    private static void getFileList(File folderList[],
                                    ArrayList<String> list) {
        for (int i = 0; i < folderList.length; i++) {
            if (folderList[i].getName().contains(".db")) {
                if (!TextUtils.isEmpty(folderList[i].getPath())) {
                    list.add(folderList[i].getPath());
                }
                Log.d(TAG, "file name " + folderList[i].getName()
                        + " path = " + folderList[i].getPath());
            }
            File fileList[] = folderList[i].listFiles();
            if (fileList != null && fileList.length > 0) {

                for (int j = 0; j < fileList.length; j++) {
                    getFileList(fileList, list);
                }

            }
        }
    }


    /**
     * Functionality to convert a size into byte, kilobyte, megabyte, gigabyte or terabyte
     * {@link <ahref http://stackoverflow.com/a/5599842/>}
     */
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static int defineExifOrientation(String imageUri) {
        int rotation = 0;

        try {
            ExifInterface exif = new ExifInterface(imageUri);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (exifOrientation) {

                case ExifInterface.ORIENTATION_NORMAL:
                    rotation = 0;

                    break;

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotation = 90;

                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotation = 180;

                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotation = 270;

                    break;
            }
        } catch (IOException e) {
            Log.e("Can't read EXIF tags from file [%s]", imageUri);
        }

        return rotation;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
