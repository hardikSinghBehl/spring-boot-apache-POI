package com.hardik.durian.annotation.aspect;

import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.durian.annotation.IsXlsx;
import com.hardik.durian.exception.InvalidExcelFileExtensionException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Aspect
@Slf4j
public class CheckExcelFormatXlsxAspect {

	private Integer headerArgumentPosition;

	@Before("execution(* *.*(..)) && @annotation(isXlsx)")
	public void checkIfExcelFileFormatIsValid(JoinPoint joinPoint, IsXlsx isXlsx) {
		log.info("Running Is Active Check On Method {}() in {}.class", joinPoint.getSignature().getName(),
				joinPoint.getSignature().getDeclaringType().getSimpleName());

		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		AtomicInteger count = new AtomicInteger(0);

		for (int i = 0; i < codeSignature.getParameterNames().length; i++) {
			if (codeSignature.getParameterNames()[i].equalsIgnoreCase("file")) {
				headerArgumentPosition = count.get();
				break;
			} else
				count.incrementAndGet();
		}

		final var file = (MultipartFile) joinPoint.getArgs()[headerArgumentPosition];

		if (!file.getOriginalFilename().endsWith(".xlsx"))
			throw new InvalidExcelFileExtensionException();
	}

}
