package service.iljali.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.common.common.CommandMap;
import service.common.util.CommonUtils;
import service.common.util.FileUtils;
import service.common.util.Nvl;
import service.iljali.service.IljaliService;

@CrossOrigin(origins = "*")
@Controller
public class IljaliController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="iljaliService")
	private IljaliService iljaliService;
	
	
	
	/*임기제 공무원*/
	@RequestMapping(value="/api/iljali/insertTermOfficialInfo.do",method=RequestMethod.POST)
	public ModelAndView insertTermOfficialInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertTermOfficialInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertTermOfficialInfo val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertTermOfficialInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/updateVolunteerTermOfficialInfo.do",method=RequestMethod.POST)
	public ModelAndView updateVolunteerTermOfficialInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateVolunteerTermOfficialInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateVolunteerTermOfficialInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateVolunteerTermOfficialInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/updateTermOfficialPaymentYN.do",method=RequestMethod.POST)
	public ModelAndView updateTermOfficialPaymentYN(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateTermOfficialPaymentYN 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateTermOfficialPaymentYN val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateTermOfficialPaymentYN(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/updateTermOfficialMaster.do",method=RequestMethod.POST)
	public ModelAndView updateTermOfficialMaster(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateTermOfficialMaster 타는지 확인1");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateTermOfficialMaster val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateTermOfficialMaster(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/deleteTermOfficialInfo.do",method=RequestMethod.POST)
	public ModelAndView deleteTermOfficialInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deleteTermOfficialInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deleteTermOfficialInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deleteTermOfficialInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/getTermOfficialInfo.do",method=RequestMethod.POST)
	public ModelAndView getTermOfficialInfo(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
		System.out.println("getTermOfficialInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("getVisitorInfo val= [" + params.toString() + "]");
		rtmap = iljaliService.getTermOfficialInfo(params);
		mv.addObject("map", rtmap);
	
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/selectTermOfficialList.do")
    public ModelAndView selectTermOfficialList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	System.out.println("selectTermOfficialList 확인");
    	
    	List<Map<String,Object>> resultList = iljaliService.selectTermOfficialList(params);
    	
    	mv.addObject("resultList", resultList);
    	
    	return mv;
    }
	
	/*기간제 근로자*/
	@RequestMapping(value="/api/iljali/insertTermWorkerInfo.do",method=RequestMethod.POST)
	public ModelAndView insertTermWorkerInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertTermWorkerInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertTermWorkerInfo val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertTermWorkerInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/updateVolunteerTermWorkerInfo.do",method=RequestMethod.POST)
	public ModelAndView updateVolunteerTermWorkerInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateVolunteerTermWorkerInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateVolunteerTermWorkerInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateVolunteerTermWorkerInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/updateTermWorkerMaster.do",method=RequestMethod.POST)
	public ModelAndView updateTermWorkerMaster(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateTermWorkerMaster 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateTermWorkerMaster val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateTermWorkerMaster(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/deleteTermWorkerInfo.do",method=RequestMethod.POST)
	public ModelAndView deleteTermWorkerInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deleteTermWorkerInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deleteTermWorkerInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deleteTermWorkerInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/getTermWorkerInfo.do",method=RequestMethod.POST)
	public ModelAndView getTermWorkerInfo(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
		System.out.println("getTermWorkerInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("getTermWorkerInfo val= [" + params.toString() + "]");
		rtmap = iljaliService.getTermWorkerInfo(params);
		mv.addObject("map", rtmap);
	
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/selectTermWorkerList.do")
    public ModelAndView selectTermWorkerList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> resultList = iljaliService.selectTermWorkerList(params);
    	
    	mv.addObject("resultList", resultList);
    	
    	return mv;
    }
	
	
	/*공공근로사업참여자*/
	@RequestMapping(value="/api/iljali/insertPublicBusinessInfo.do",method=RequestMethod.POST)
	public ModelAndView insertPublicBusinessInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertPublicBusinessInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertPublicBusinessInfo val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertPublicBusinessInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/updateVolunteerPublicBusinessInfo.do",method=RequestMethod.POST)
	public ModelAndView updateVolunteerPublicBusinessInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updateVolunteerPublicBusinessInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updateVolunteerPublicBusinessInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updateVolunteerPublicBusinessInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/updatePublicBusinessMaster.do",method=RequestMethod.POST)
	public ModelAndView updatePublicBusinessMaster(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("updatePublicBusinessMaster 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("updatePublicBusinessMaster val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.updatePublicBusinessMaster(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value="/api/iljali/deletePublicBusinessInfo.do",method=RequestMethod.POST)
	public ModelAndView deletePublicBusinessInfo(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deletePublicBusinessInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deletePublicBusinessInfo val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deletePublicBusinessInfo(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/getPublicBusinessInfo.do",method=RequestMethod.POST)
	public ModelAndView getPublicBusinessInfo(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
		System.out.println("getPublicBusinessInfo 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("getPublicBusinessInfo val= [" + params.toString() + "]");
		rtmap = iljaliService.getPublicBusinessInfo(params);
		mv.addObject("map", rtmap);
	
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/selectPublicBusinessList.do")
    public ModelAndView selectPublicBusinessList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> resultList = iljaliService.selectPublicBusinessList(params);
    	
    	mv.addObject("resultList", resultList);
    	
    	return mv;
    }
	
	
	/*고시공고 좋아요*/
	@RequestMapping(value="/api/iljali/insertGood.do",method=RequestMethod.POST)
	public ModelAndView insertGood(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertGood 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertGood val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertGood(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/deleteGood.do",method=RequestMethod.POST)
	public ModelAndView deleteGood(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deleteGood 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deleteGood val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deleteGood(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/getGood.do",method=RequestMethod.POST)
	public ModelAndView getGood(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("getGood 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> rtmap = new HashMap<>();
		boolean result = false;
		
		log.debug("getGood val= [" + params.toString() + "]");
		try {
			rtmap = iljaliService.getGood(params);
			if(rtmap.get("uuid").equals(params.get("uuid"))){ //아이디값이 동일할경우 true 리턴
				result = true;
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			result = false;
		}
		

		
		mv.addObject("result", result);
	
		return mv;
	}
	

	
	
	/*고시공고 스크랩*/
	@RequestMapping(value="/api/iljali/insertScrap.do",method=RequestMethod.POST)
	public ModelAndView insertScrap(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertScrap 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertScrap val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertScrap(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/deleteScrap.do",method=RequestMethod.POST)
	public ModelAndView deleteScrap(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deleteScrap 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deleteScrap val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deleteScrap(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/getScrap.do",method=RequestMethod.POST)
	public ModelAndView getScrap(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("getScrap 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> rtmap = new HashMap<>();
		boolean result = false;
		
		log.debug("getScrap val= [" + params.toString() + "]");
		try {
			rtmap = iljaliService.getScrap(params);
			
			if(rtmap.get("uuid").equals(params.get("uuid"))){ //아이디값이 동일할경우 true 리턴
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
	
		return mv;
	}
	
	@RequestMapping(value="/api/iljali/selectMyScrapList.do",method=RequestMethod.POST)
    public ModelAndView selectMyScrapList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	System.out.println("selectMyScrapList 확인");
    	
    	List<Map<String,Object>> resultList = iljaliService.selectMyScrapList(params);
    	
    	mv.addObject("resultList", resultList);
    	
    	return mv;
    }
	
	//AWS에서 파일 send
    @RequestMapping(value="/api/iljali/sendIljaliFile.do", method=RequestMethod.POST)
	public ModelAndView sendIljaliFile(@RequestParam("attachFile") List<MultipartFile> fileList,
			@RequestParam("not_ancmt_mgt_no") String not_ancmt_mgt_no,
			@RequestParam("uuid") String uuid,
			HttpServletRequest request) throws IOException{
		
        ModelAndView mv = new ModelAndView("jsonView");
        
        Map<String, Object> map = new HashMap<String,Object>();

    	String filePath = "C:\\dev\\file\\service\\iljali\\"+ uuid + "\\" + not_ancmt_mgt_no + "\\"; //로컬
    	
    	if(request.getServerName().equals("public.gangnam.go.kr"))  //운영	
			filePath = "C:\\server\\file\\service\\iljali\\"+ uuid + "\\" + not_ancmt_mgt_no + "\\";
    	
        
    	System.out.println("sendIljaliFile filePath 확인 : " + filePath);
    	
    	
    	
    	//경로가 없을경우 경로 생성
        File file = new File(filePath);
        if(file.exists() == false){
        	file.mkdirs();
        }
        
		String getOrgFileName = "";
		
		boolean result = false;
        
		try {
		    for(MultipartFile f : fileList){
		    	
				getOrgFileName= f.getOriginalFilename();
		
		    	
		    	// 파일이 실제 저장될때 고유의 값으로 저장 되도록 random 값으로 파일명 설정
		    	String savedName = getOrgFileName;
		    	
		    	File target = new File(filePath,savedName);
		    	FileCopyUtils.copy(f.getBytes(), target);
		    }
        
			result = true;
		} catch (Exception e) {
			result = false;
		}
        
        mv.addObject("result", result);

    	return mv;
    }
    
    
    //더강남앱에서 파일 get
    @RequestMapping(value="/api/iljali/getIljaliFile.do", method=RequestMethod.POST)
	public ModelAndView getIljaliFile(@RequestBody Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("getIljaliFile 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, Object> rtmap = new HashMap<>();
		
		rtmap = iljaliService.selectFile(params);
		boolean result = false;
		
    	String filePath = "C:\\dev\\file\\service\\iljali\\" + (String) rtmap.get("uuid") +"\\" + (String) rtmap.get("not_ancmt_mgt_no") + "\\"; //로컬
    	
    	if(request.getServerName().equals("public.gangnam.go.kr"))  //운영	
			filePath = "C:\\server\\file\\service\\iljali\\" + (String) rtmap.get("uuid") +"\\" + (String) rtmap.get("not_ancmt_mgt_no") + "\\";
        
    	System.out.println("getIljaliFile filePath 확인 : " + filePath);
    	
    	// 파일 업로드된 경로
	    String savePath = filePath;

	    String filename = (String) rtmap.get("file_name");

	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

	    try{
	        // 파일을 읽어 스트림에 담기
	        try{
	            file = new File(savePath, filename);
	            in = new FileInputStream(file);
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }

	        // 파일 다운로드 헤더 지정
	        response.reset() ;
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        if(!skip){
	        	
                encodedFilename = "\"" + filename + "\"";
            	
	        	System.out.println("encodedFilename 확인 : " + encodedFilename);
                response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            response.setHeader ("Content-Length", ""+file.length() );

	            os = response.getOutputStream();
	            byte b[] = new byte[(int)file.length()];
	            int leng = 0;

	            while( (leng = in.read(b)) > 0 ){
	                os.write(b,0,leng);
	            }
	            
	            result = true;
	        }else{
	            response.setContentType("text/html;charset=UTF-8");
	            result = false;
	        }

	        in.close();
	        os.close();
	        
	        

	    }catch(Exception e){
	    	result = false;
	    }
    	
	    mv.addObject("result", result);

    	return mv;
    }
    
    //파일관리 insert
	@RequestMapping(value="/api/iljali/insertMangeIljaliFile.do",method=RequestMethod.POST)
	public ModelAndView insertMangeIljaliFile(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("insertMangeIljaliFile 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("insertMangeIljaliFile val= [" + params.toString() + "]");
		
		boolean result = false;
			
		try {
			rtmap = iljaliService.insertMangeIljaliFile(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
	
		mv.addObject("result", result);
		
			
		return mv;
	}
    
    
    //파일관리 delete
	@RequestMapping(value="/api/iljali/deleteMangeIljaliFile.do",method=RequestMethod.POST)
	public ModelAndView deleteMangeIljaliFile(@RequestBody Map<String, Object> params,HttpServletRequest request){
		System.out.println("deleteMangeIljaliFile 타는지 확인");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		log.debug("deleteMangeIljaliFile val= [" + params.toString() + "]");
		
		boolean result = false;
		
		try {
			rtmap = iljaliService.deleteMangeIljaliFile(params);
			
			if(rtmap.get("result").equals(false)){ //수행오류가 생길경우
				result = false;
			}else{
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
    
    //파일관리 select
	@RequestMapping(value="/api/iljali/selectMangeIljaliFileList.do")
    public ModelAndView selectMangeIljaliFileList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	System.out.println("selectMangeIljaliFileList 확인");
    	
    	List<Map<String,Object>> resultList = iljaliService.selectMangeIljaliFileList(params);
    	
    	mv.addObject("resultList", resultList);
    	
    	return mv;
    }
	
    
    /*************************************************지워도 됨 안만들어도 되는 화면단임*******************************************/
    
    @RequestMapping(value="/iljali/iljaliLogin.do")
    public ModelAndView iljaliLogin(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/iljali/iljaliLogin");
    	return mv;
    }
	
	@RequestMapping(value="/iljali/iljaliList.do")
    public ModelAndView iljaliList(CommandMap commandMap) throws Exception{
    	
		ModelAndView mv = new ModelAndView("/iljali/iljaliList");
    	return mv;
    }
	
	@RequestMapping(value="/iljali/selectTermOfficialViewList.do")
    public ModelAndView selectTermOfficialViewList(CommandMap commandMap) throws Exception{
		log.debug("selectTermOfficialViewList param 확인 : [" + commandMap.getMap().toString() + "]");
    	//제우스 서버에서 깨지는거 떄문에 이렇게 넣음
    	commandMap.put("name", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("name").toString())));
    	commandMap.put("gonggo_nm", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("gonggo_nm").toString())));
		
		ModelAndView mv = new ModelAndView("jsonView");
    	
		/*응시자 정보 조회*/
    	List<Map<String,Object>> termOfficialList = iljaliService.selectTermOfficialViewList(commandMap.getMap());
    	
    	mv.addObject("termOfficialList", termOfficialList);
    	mv.addObject("termOfficial_total", 0);
    	
    	if( termOfficialList.size()!=0){
    		mv.addObject("termOfficial_total", termOfficialList.get(0).get("TOTAL_COUNT"));
    	}
    	System.out.println("selectTermOfficialViewList 마지막");
    	return mv;
    }
	
	
	@RequestMapping(value="/iljali/selectTermWorkerViewList.do")
    public ModelAndView selectTermWorkerViewList(CommandMap commandMap) throws Exception{
		log.debug("selectTermWorkerViewList param 확인 : [" + commandMap.getMap().toString() + "]");
    	//제우스 서버에서 깨지는거 떄문에 이렇게 넣음
    	commandMap.put("name", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("name").toString())));
    	commandMap.put("gonggo_nm", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("gonggo_nm").toString())));
		
		ModelAndView mv = new ModelAndView("jsonView");
    	
		/*응시자 정보 조회*/
    	List<Map<String,Object>> termWorkerList = iljaliService.selectTermWorkerViewList(commandMap.getMap());
    	
    	mv.addObject("termWorkerList", termWorkerList);
    	mv.addObject("termWorker_total", 0);
    	
    	if( termWorkerList.size()!=0){
    		mv.addObject("termWorker_total", termWorkerList.get(0).get("TOTAL_COUNT"));
    	}
    	
    	System.out.println("selectTermWorkerViewList 마지막");
    	
    	return mv;
    }
	
	@RequestMapping(value="/iljali/selectPublicBusinessViewList.do")
    public ModelAndView selectPublicBusinessViewList(CommandMap commandMap) throws Exception{
		log.debug("selectPublicBusinessViewList param 확인 : [" + commandMap.getMap().toString() + "]");
    	//제우스 서버에서 깨지는거 떄문에 이렇게 넣음
    	commandMap.put("name", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("name").toString())));
    	commandMap.put("gonggo_nm", Nvl.nvlStr(CommonUtils.ascToksc(commandMap.getMap().get("gonggo_nm").toString())));
		
		ModelAndView mv = new ModelAndView("jsonView");
    	
		/*응시자 정보 조회*/
    	List<Map<String,Object>> publicBusinessList = iljaliService.selectPublicBusinessViewList(commandMap.getMap());
    	
    	mv.addObject("publicBusinessList", publicBusinessList);
    	mv.addObject("publicBusiness_total", 0);
    	
    	if( publicBusinessList.size()!=0){
    		mv.addObject("publicBusiness_total", publicBusinessList.get(0).get("TOTAL_COUNT"));
    	}
    	
    	return mv;
    }
	
	
	/*상세 페이지-임기제  공무원*/
	@RequestMapping(value="/iljali/iljaliTermOfficialDtl.do")
	public ModelAndView iljaliTermOfficialDtl(CommandMap commandMap) throws Exception{
		System.out.println("iljaliTermOfficialDtl 탐");
		System.out.println("commandMap.getMap() 확인 : " + commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("/iljali/iljaliTermOfficialDtl");
		
		Map<String,Object> map = iljaliService.getTermOfficialInfo(commandMap.getMap());
		
		System.out.println("map 값 확인 : " + map.toString());
		mv.addObject("map", map);
				
		return mv;
	}
	
	/*상세 페이지-기간제 근로자*/
	@RequestMapping(value="/iljali/iljaliTermWorkerDtl.do")
	public ModelAndView iljaliTermWorkerDtl(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/iljali/iljaliTermWorkerDtl");
		
		Map<String,Object> map = iljaliService.getTermWorkerInfo(commandMap.getMap());
		mv.addObject("map", map);
				
		return mv;
	}
	
	/*상세 페이지-공공사업참여자*/
	@RequestMapping(value="/iljali/iljaliPublicBusinessDtl.do")
	public ModelAndView iljaliPublicBusinessDtl(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/iljali/iljaliPublicBusinessDtl");
		
		Map<String,Object> map = iljaliService.getPublicBusinessInfo(commandMap.getMap());
		mv.addObject("map", map);
				
		return mv;
	}
	/*************************************************지워도 됨 안만들어도 되는 화면단임*******************************************/
	
	
	
	@RequestMapping(value="/api/iljali/selectAdminList.do")
    public ModelAndView selectAdminList(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
		log.debug("selectAdminList param 확인 : [" + params.toString() + "]");

		//제우스 서버에서 깨지는거 떄문에 이렇게 넣음
		params.put("gonggo_nm", Nvl.nvlStr(CommonUtils.ascToksc((String)params.get("gonggo_nm"))));
		
		
		int pageIndex = Integer.parseInt((String)params.get("pageIndex"));
		int pageCount = Integer.parseInt((String)params.get("pageCount"));
	        
        int curRange = (int)((pageIndex-1)/pageCount) + 1;
		int start = (curRange - 1) * pageCount + 1;
		int end = start + pageCount - 1;
		
		params.put("START1",start);
		params.put("END1", end);
		
		log.debug("selectAdminList param 확인2 : [" + params.toString() + "]");
		
		/*응시자 정보 조회*/
    	List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		switch((String)params.get("gonggo_gubun").toString()) {
	    case "imgi": 
	    		resultList = iljaliService.selectTermOfficialViewList(params);
	         break;
	    case "gigan": 
	    		resultList = iljaliService.selectTermWorkerViewList(params);
	         break;
	    case "gonggong":
	    		resultList = iljaliService.selectPublicBusinessViewList(params);	
        	break;
		}
   	
		ModelAndView mv = new ModelAndView("jsonView");
		
    	mv.addObject("resultList", resultList);
    	mv.addObject("TotalCount", 0);

    	
    	
    	if( resultList.size()!=0){
    		mv.addObject("TotalCount", resultList.get(0).get("TOTAL_COUNT"));
    	}
    	
    	System.out.println("selectAdminList 마지막");
    	
    	return mv;
    }
	
	@RequestMapping(value="/api/iljali/getFileExistYN.do",method=RequestMethod.POST)
	public ModelAndView getFileExistYN(@RequestBody Map<String, Object> params,HttpServletRequest request) throws Exception{
		System.out.println("getFileExistYN 타는지 확인");
		log.debug("getFileExistYN val= [" + params.toString() + "]");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> rtmap = new HashMap<>();
		
		
		String uuid = (String) params.get("uuid");
		String not_ancmt_mgt_no = (String) params.get("not_ancmt_mgt_no");
		String file_name = (String) params.get("file_name");

		String filePath = "C:\\dev\\file\\service\\iljali\\"+ uuid + "\\" + not_ancmt_mgt_no + "\\" + file_name; //로컬
    	
    	if(request.getServerName().equals("public.gangnam.go.kr"))  //운영	
			filePath = "C:\\server\\file\\service\\iljali\\"+ uuid + "\\" + not_ancmt_mgt_no + "\\" + file_name;
    	
        
    	System.out.println("sendIljaliFile filePath 확인 : " + filePath);
		
		File f = new File(filePath);

		System.out.println("f.exists() 확인 : " + f.exists());
		
		boolean result = f.exists();
		
		mv.addObject("result", result);
		
		return mv;
	}
	
}
