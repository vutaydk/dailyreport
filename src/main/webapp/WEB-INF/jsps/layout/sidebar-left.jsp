<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>

                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a href="project" class="nav-link">
                            <i class="far fa-folder-open"></i>
                            <m:message key="label.project.add" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="project/chart" class="nav-link">
                            <i class="fas fa-chart-bar"></i>
                            <m:message key="label.project.chart" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="report" class="nav-link">
                            <i class="fas fa-users"></i>
                            <m:message key="label.project.manament" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="task" class="nav-link">
                            <i class="fas fa-list-ul"></i>
                            <m:message key="label.task" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="rights" class="nav-link">
                            <i class="fas fa-key"></i>
                            <m:message key="label.rights" />
                        </a>
                    </li>
                </ul>