package com.ape.factory;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioSystem;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.ape.factory.application.FactoryApp;
import com.tn.FtmLog;

import java.util.Map;

import static com.android.internal.telephony.PhoneConstants.PHONE_TYPE_CDMA;

public class Utils {

    public static final String TAG = "Utils";
    public static boolean READ_NV_USE_QCNVITEMS_INTERFACE = false;

    public static int testItemOffset = 0;
    public static final String TEST_TYPE = "type";
    public static final String TEST_SCENE = "test_scene";
    public static final String TEST_TYPE_MMI1 = "type_mmi1";
    public static final String TEST_TYPE_SENSOR = "type_sensor";
    public static final String INTENT_ENTRY_TYPE = "mEntryType";

    public static final int ITEM_TEST_TYPE = 0;
    public static final int FULL_TEST_TYPE = 1;
    public static final int LDA_TEST_TYPE = 2;
    public static final int PAT_TEST_TYPE = 3;
    public static final int SENSOR_TEST_TYPE = 4;
    public static final int PCBA_TEST_TYPE = 5;
    public static final int SUBBOARD_TEST_TYPE = 6;
    public static final int PAT2_TEST_TYPE = 7;

    // 设置进入的类型
    public static String entryType;

    public String mSupportedKeys = "0";
    public String mTpType = "0";
    public String mDontCombineItems = "0";
    public String mTinyMixVolume = "0";
    public String mTpVersionFile = "0";
    public String mAudioCaliCommand = "0";
    public String mReceiverCali = "0";
    public String mReceiverCaliMinValue = "6.8";
    public String mReceiverCaliMaxValue = "9.2";
    public String mLoopbackPeriod = "0";

    //daming.cao,for MicRecorder test
    public String mMainMicTC = "3";
    public String mSubMicTC = "4";
    public String mEarphoneTC = "5";

    /**
     * 这个是控制，pass菜单，不依赖sd的状态
     **/
    public boolean mSkipSdCheck = false;
    /**
     * 这个是不需要显示sd的状态
     **/
    public boolean mSDCardSupport = false;
    public boolean mHasMic2 = true;
    public boolean mNoFrontFlash = false;
    public boolean mShowMeid = false;
    public boolean mShowPesn = false;
    public boolean mDualCamVerifySupport = false;
    public boolean mFpTestSupport = false;
    public boolean mFlashLedOpenCamera = false;
    public boolean mGSensorCaliUnneccessary = false;
    public boolean mGyroscopeCaliUnneccessary = false;
    public boolean mPsCaliUnneccessary = false;
    public boolean mLcdBlackReduceBrightness = false;

    public boolean EarPhone_Key = true;
    public boolean EarPhone = true;
    public boolean DoubleSpk = false;
    public boolean PSensor = false;

    public boolean mNFCSupported = false;

    private static TelephonyManager mTelephonyManager;
    private final static String IS_FACTORY_VERSION = SystemProperties
            .get("ro.odm.lenovo.factory", "false");

    private static Utils sInstance;

    public static Utils getInstance() {
        if (sInstance == null) {
            sInstance = new Utils();
        }
        return sInstance;
    }

    void resetConfig(Map<String, String> configsList) {
        if (configsList.size() > 0) {
            for (Map.Entry<String, String> entry : configsList.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                FtmLog.i(TAG, "key = " + key + ", value = " + value);
                if("keys_MENU_HOME_BACK_BACKTOUCH_SUGAR".equals(key)){
                    mSupportedKeys =value;
                }else if ("TP".equals(key)) {
                    mTpType = value;
                } else if ("DontRunItemsInBackground".equals(key)) {
                    mDontCombineItems = value;
                } else if ("loopback_volume".equals(key)) {
                    mTinyMixVolume = value;
                } else if ("Tp_version_file".equals(key)) {
                    mTpVersionFile = value;
                } else if ("Audio_Cali_Command".equals(key)) {
                    mAudioCaliCommand = value;//Speaker Cali
                } else if ("Receiver_Cali".equals(key)) {
                    mReceiverCali = value;//Receiver Cali
                } else if ("Receiver_Cali_Min".equals(key)) {
                    mReceiverCaliMinValue = value;//Receiver Cali Min
                } else if ("Receiver_Cali_Max".equals(key)) {
                    mReceiverCaliMaxValue = value;//Receiver Cali Max
                } else if ("Loopback_period".equals(key)) {
                    mLoopbackPeriod = value;
                } else if ("MainMic_tc".equals(key)) {
                    mMainMicTC = value;
                } else if ("SubMic_tc".equals(key)) {
                    mSubMicTC = value;
                } else if ("Earphone_tc".equals(key)) {
                    mEarphoneTC = value;
                }  else if ("EarPhone_Key".equals(key)) {
                    if ("1".equals(value)) {
                        EarPhone_Key = true;
                    } else {
                        EarPhone_Key = false;
                    }
                }else if ("EarPhone".equals(key)) {
                    if ("1".equals(value)) {
                        EarPhone = true;
                    } else {
                        EarPhone = false;
                    }
                } else if ("DoubleSpeaker".equals(key)) {
                    if ("1".equals(value)) {
                        DoubleSpk = true;
                    } else {
                        DoubleSpk = false;
                    }
                }
                else if ("Skip_SD_check".equals(key)) {
                    if ("1".equals(value)) {
                        mSkipSdCheck = true;
                    } else {
                        mSkipSdCheck = false;
                    }
                } else if ("SD_Test_Support".equals(key)) {
                    if ("1".equals(value)) {
                        mSDCardSupport = true;
                    } else {
                        mSDCardSupport = false;
                    }
                } else if ("Mic_2".equals(key)) {
                    if ("1".equals(value)) {
                        mHasMic2 = true;
                    } else {
                        mHasMic2 = false;
                    }
                } else if ("no_front_flash".equals(key)) {
                    if ("1".equals(value)) {
                        mNoFrontFlash = true;
                    } else {
                        mNoFrontFlash = false;
                    }
                } else if ("Show_MEID".equals(key)) {
                    if ("1".equals(value)) {
                        mShowMeid = true;
                    } else {
                        mShowMeid = false;
                    }
                } else if ("Show_PESN".equals(key)) {
                    if ("1".equals(value)) {
                        mShowPesn = true;
                    } else {
                        mShowPesn = false;
                    }
                } else if ("camfaccalibration".equals(key)) {
                    if ("1".equals(value)) {
                        mDualCamVerifySupport = true;
                    } else {
                        mDualCamVerifySupport = false;
                    }
                } else if ("Fp_Test_supported".equals(key)) {
                    if ("1".equals(value)) {
                        mFpTestSupport = true;
                    } else {
                        mFpTestSupport = false;
                    }
                } else if ("FlashLed_Torch".equals(key)) {
                    if (!"1".equals(value)) {
                        mFlashLedOpenCamera = true;
                    } else {
                        mFlashLedOpenCamera = false;
                    }
                } else if ("gsensor_unneccessary_calibrate".equals(key)) {
                    if ("1".equals(value)) {
                        mGSensorCaliUnneccessary = true;
                    } else {
                        mGSensorCaliUnneccessary = false;
                    }
                } else if ("gyroscope_unneccessary_calibrate".equals(key)) {
                    if ("1".equals(value)) {
                        mGyroscopeCaliUnneccessary = true;
                    } else {
                        mGyroscopeCaliUnneccessary = false;
                    }
                } else if ("ps_unneccessary_calibrate".equals(key)) {
                    if ("1".equals(value)) {
                        mPsCaliUnneccessary = true;
                    } else {
                        mPsCaliUnneccessary = false;
                    }
                } else if ("Lcd_black_reduce_brightness".equals(key)) {
                    if ("1".equals(value)) {
                        mLcdBlackReduceBrightness = true;
                    } else {
                        mLcdBlackReduceBrightness = false;
                    }
                } else if ("NFC_supported".equals(key)) {
                    if ("1".equals(value)) {
                        mNFCSupported = true;
                    } else {
                        mNFCSupported = false;
                    }
                } else if ("ReadNv_use_qcNvItems_interface".equals(key)) {
                    if ("1".equals(value)) {
                        READ_NV_USE_QCNVITEMS_INTERFACE = true;
                    } else {
                        READ_NV_USE_QCNVITEMS_INTERFACE = false;
                    }
                }
            }
        }
    }

    /**
     * 16进制byte数组转String
     *
     * @param b
     * @return
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }
        FtmLog.i(TAG, "bytes2HexString r: " + r);
        return r;
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }
    }


    /**
     * 字符转换为字节
     *
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] string2ByteArr(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        char[] tempChar = result.toCharArray();
        byte[] temp = new byte[tempChar.length];
        for (int i = 0; i < tempChar.length; i++) {
            temp[i] = (byte) tempChar[i];
        }
        return temp;
    }

    public static String byteArr2String(byte[] result) {
        if (result == null || result.length < 0) {
            return null;
        }
        String temp = new String(result);
        FtmLog.i(TAG, "byteArr2String temp : " + temp);
        return temp;
    }

    /**
     * daming.cao,获取imei号
     *
     * @param context
     * @return
     */
    public static String getAllPhoneImei(Context context) {
        initTelephonyManager(context);
        if (mTelephonyManager == null) {
            android.util.Log.i(TAG, "TelephonyManager is null , please init TelephonyManager");
            return "";
        }

        int PhoneCount = mTelephonyManager.getPhoneCount();
        FtmLog.i(TAG, " PhoneCount =" + PhoneCount);
        StringBuilder imei = new StringBuilder();
        for (int simSlotNumber = 0; simSlotNumber < PhoneCount; simSlotNumber++) {
            FtmLog.i(TAG, " simSlotNumber =" + simSlotNumber);
            if (simSlotNumber == 1) {
                imei.append("\n");
            }
            imei.append(getPhoneImei(context, simSlotNumber));
        }
        return imei.toString();
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneImei(Context context, int simSlot) {
        initTelephonyManager(context);
        if (mTelephonyManager == null) {
            FtmLog.i(TAG, "TelephonyManager is null , please init TelephonyManager");
            return "";
        }
        final int phoneType = getPhoneType(context, simSlot);
        return phoneType == PHONE_TYPE_CDMA ? mTelephonyManager.getMeid(simSlot) : mTelephonyManager.getImei(simSlot);
    }

    public static int getPhoneType(Context context, int slotIndex) {
        initTelephonyManager(context);
        if (mTelephonyManager == null) {
            FtmLog.i(TAG, "TelephonyManager is null , please init TelephonyManager");
            return -1;
        }
        return mTelephonyManager.getCurrentPhoneTypeForSlot(slotIndex);
    }

    public static void initTelephonyManager(Context context) {
        if (mTelephonyManager == null) {
            mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }
    }

    /**
     * 是否是工厂版本的开关
     *
     * @return
     */
    public static boolean isFactoryVersion() {
        if (IS_FACTORY_VERSION.equals("true")){
            return true;
        }
        return false;
    }

    public static boolean isMonkeyProcess(){
        if (ActivityManager.isUserAMonkey()) {
            return true;
        }
        return false;
    }

    public static int getVolumeDefault(int type) {
        if (type == AudioSystem.STREAM_MUSIC) {
            int defaultMusicVolume = SystemProperties.getInt("ro.config.media_vol_default", -1);
            if (defaultMusicVolume == -1) {
                return AudioSystem.DEFAULT_STREAM_VOLUME[AudioSystem.STREAM_MUSIC];
            }

        } else if (type == AudioSystem.STREAM_VOICE_CALL) {
            int defaultCallVolume = SystemProperties.getInt("ro.config.vc_call_vol_default", -1);
            if (defaultCallVolume == -1) {
                return AudioSystem.DEFAULT_STREAM_VOLUME[AudioSystem.STREAM_VOICE_CALL];
            }
        }
        return -1;
    }

    public static boolean isNeedCaliBt(){
        return FactoryApp.IS_NEED_CALI_BT;
    }

    public static void setCaliBtEnable(boolean able,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("FTM", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("enable_cali_bt",able).apply();
    }

    public static boolean isWifiOnly(){
        boolean isWifiOnly = true;
        String board = SystemProperties.get("ro.product.board","t6100a_wifi");
        if ("t6100a_wifi".equals(board)) {
            isWifiOnly = true;
        } else {
            isWifiOnly = false;
        }
        return isWifiOnly;
    }
}

