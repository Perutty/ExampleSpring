package co.edu.ufps.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.ufps.commands.GenericServiceImpl;
import co.edu.ufps.model.Proyect;
import co.edu.ufps.repository.ProyectRepository;
import co.edu.ufps.services.ProyectService;

@Service
public class ProyectServiceImpl extends GenericServiceImpl<Proyect, Integer> implements ProyectService{

	@Autowired
	private ProyectRepository proyectRepository;
	
	@Override
	public CrudRepository<Proyect, Integer> getDao() {
		return proyectRepository;
	}

	@Override
	public Proyect select(String titulo) {
		return proyectRepository.findByTitulo(titulo);
	}
	
	public ByteArrayInputStream exportAllData() throws Exception {
		String [] columns = {"Id", "Titulo", "Objetivo", "Notas", "Fecha Inicio", "Fecha Fin", "Estado", "Fecha Creacion"};
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Sheet sheet = workbook.createSheet("Proyectos");
		Row row = sheet.createRow(0);
		for(int i=0; i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		List<Proyect> proyects = this.getAll();
		int initRow = 1;
		for(Proyect proyect : proyects) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(proyect.getId());
			row.createCell(1).setCellValue(proyect.getTitulo());
			row.createCell(2).setCellValue(proyect.getObjetivo());
			row.createCell(3).setCellValue(proyect.getNotas());
			row.createCell(4).setCellValue(proyect.getFechainicio());
			row.createCell(5).setCellValue(proyect.getFechafin());
			row.createCell(6).setCellValue(proyect.getEstado());
			row.createCell(7).setCellValue(proyect.getFechacreacion());
			initRow++;
		}
	
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

}
