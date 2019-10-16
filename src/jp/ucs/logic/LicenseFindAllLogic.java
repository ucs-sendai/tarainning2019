package jp.ucs.logic;

import java.util.Map;

import jp.ucs.dao.LicenseDAO;
import jp.ucs.exception.HrsmUcsDBException;

public class LicenseFindAllLogic {
	    public Map<String, String> licenseExecute() throws HrsmUcsDBException{
	        LicenseDAO licensesDAO = new LicenseDAO();
	        return licensesDAO.licenseFindAll();
	    }
	}



