/*!
 * Bootstrap-select v1.7.2 (http://silviomoreto.github.io/bootstrap-select)
 *
 * Copyright 2013-2015 bootstrap-select
 * Licensed under MIT (https://github.com/silviomoreto/bootstrap-select/blob/master/LICENSE)
 */

.bootstrap-select {
  width: 220px \0;
  /*IE9 and below*/
}
.bootstrap-select > .dropdown-toggle {
  width: 100%;
  padding-right: 25px;
}
.has-error .bootstrap-select .dropdown-toggle,
.error .bootstrap-select .dropdown-toggle {
  border-color: #b94a48;
}
.bootstrap-select.fit-width {
  width: auto !important;
}
.bootstrap-select:not([class*="col-"]):not([class*="form-control"]):not(.input-group-btn) {
  width: 220px;
}
.bootstrap-select .dropdown-toggle:focus {
  outline: thin dotted #333333 !important;
  outline: 5px auto -webkit-focus-ring-color !important;
  outline-offset: -2px;
}
.bootstrap-select.form-control {
  margin-bottom: 0;
  padding: 0;
  border: none;
}
.bootstrap-select.form-control:not([class*="col-"]) {
  width: 100%;
}
.bootstrap-select.form-control.input-group-btn {
  z-index: auto;
}
.bootstrap-select.btn-group:not(.input-group-btn),
.bootstrap-select.btn-group[class*="col-"] {
  float: none;
  display: inline-block;
  margin-left: 0;
}
.bootstrap-select.btn-group.dropdown-menu-right,
.bootstrap-select.btn-group[class*="col-"].dropdown-menu-right,
.row .bootstrap-select.btn-group[class*="col-"].dropdown-menu-right {
  float: right;
}
.form-inline .bootstrap-select.btn-group,
.form-horizontal .bootstrap-select.btn-group,
.form-group .bootstrap-select.btn-group {
  margin-bottom: 0;
}
.form-group-lg .bootstrap-select.btn-group.form-control,
.form-group-sm .bootstrap-select.btn-group.form-control {
  padding: 0;
}
.form-inline .bootstrap-select.btn-group .form-control {
  width: 100%;
}
.bootstrap-select.btn-group.disabled,
.bootstrap-select.btn-group > .disabled {
  cursor: not-allowed;
}
.bootstrap-select.btn-group.disabled:focus,
.bootstrap-select.btn-group > .disabled:focus {
  outline: none !important;
}
.bootstrap-select.btn-group .dropdown-toggle .filter-option {
  display: inline-block;
  overflow: hidden;
  width: 100%;
  text-align: left;
}
.bootstrap-select.btn-group .dropdown-toggle .caret {
  position: absolute;
  top: 50%;
  right: 12px;
  margin-top: -2px;
  vertical-align: middle;
}
.bootstrap-select.btn-group[class*="col-"] .dropdown-toggle {
  width: 100%;
}
.bootstrap-select.btn-group .dropdown-menu {
  min-width: 100%;
  z-index: 1035;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.bootstrap-select.btn-group .dropdown-menu.inner {
  position: static;
  float: none;
  border: 0;
  padding: 0;
  margin: 0;
  border-radius: 0;
  -webkit-box-shadow: none;
          box-shadow: none;
}
.bootstrap-select.btn-group .dropdown-menu li {
  position: relative;
}
.bootstrap-select.btn-group .dropdown-menu li.active small {
  color: #fff;
}
.bootstrap-select.btn-group .dropdown-menu li.disabled a {
  cursor: not-allowed;
}
.bootstrap-select.btn-group .dropdown-menu li a {
  cursor: pointer;
}
.bootstrap-select.btn-group .dropdown-menu li a.opt {
  position: relative;
  padding-left: 2.25em;
}
.bootstrap-select.btn-group .dropdown-menu li a span.check-mark {
  display: none;
}
.bootstrap-select.btn-group .dropdown-menu li a span.text {
  display: inline-block;
}
.bootstrap-select.btn-group .dropdown-menu li small {
  padding-left: 0.5em;
}
.bootstrap-select.btn-group .dropdown-menu .notify {
  position: absolute;
  bottom: 5px;
  width: 96%;
  margin: 0 2%;
  min-height: 26px;
  padding: 3px 5px;
  background: #f5f5f5;
  border: 1px solid #e3e3e3;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
  pointer-events: none;
  opacity: 0.9;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.bootstrap-select.btn-group .no-results {
  padding: 3px;
  background: #f5f5f5;
  margin: 0 5px;
  white-space: nowrap;
}
.bootstrap-select.btn-group.fit-width .dropdown-toggle .filter-option {
  position: static;
}
.bootstrap-select.btn-group.fit-width .dropdown-toggle .caret {
  position: static;
  top: auto;
  margin-top: -1px;
}
.bootstrap-select.btn-group.show-tick .dropdown-menu li.selected a span.check-mark {
  position: absolute;
  display: inline-block;
  right: 15px;
  margin-top: 5px;
}
.bootstrap-select.btn-group.show-tick .dropdown-menu li a span.text {
  margin-right: 34px;
}
.bootstrap-select.show-menu-arrow.open > .dropdown-toggle {
  z-index: 1036;
}
.bootstrap-select.show-menu-arrow .dropdown-toggle:before {
  content: '';
  border-left: 7px solid transparent;
  border-right: 7px solid transparent;
  border-bottom: 7px solid rgba(204, 204, 204, 0.2);
  position: absolute;
  bottom: -4px;
  left: 9px;
  display: none;
}
.bootstrap-select.show-menu-arrow .dropdown-toggle:after {
  content: '';
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-bottom: 6px solid white;
  position: absolute;
  bottom: -4px;
  left: 10px;
  display: none;
}
.bootstrap-select.show-menu-arrow.dropup .dropdown-toggle:before {
  bottom: auto;
  top: -3px;
  border-top: 7px solid rgba(204, 204, 204, 0.2);
  border-bottom: 0;
}
.bootstrap-select.show-menu-arrow.dropup .dropdown-toggle:after {
  bottom: auto;
  top: -3px;
  border-top: 6px solid white;
  border-bottom: 0;
}
.bootstrap-select.show-menu-arrow.pull-right .dropdown-toggle:before {
  right: 12px;
  left: auto;
}
.bootstrap-select.show-menu-arrow.pull-right .dropdown-toggle:after {
  right: 13px;
  left: auto;
}
.bootstrap-select.show-menu-arrow.open > .dropdown-toggle:before,
.bootstrap-select.show-menu-arrow.open > .dropdown-toggle:after {
  display: block;
}
.bs-searchbox,
.bs-actionsbox,
.bs-donebutton {
  padding: 4px 8px;
}
.bs-actionsbox {
  float: left;
  width: 100%;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.bs-actionsbox .btn-group button {
  width: 50%;
}
.bs-donebutton {
  float: left;
  width: 100%;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.bs-donebutton .btn-group button {
  width: 100%;
}
.bs-searchbox + .bs-actionsbox {
  padding: 0 8px 4px;
}
.bs-searchbox .form-control {
  margin-bottom: 0;
  width: 100%;
}
select.bs-select-hidden,
select.selectpicker {
  display: none !important;
}
select.mobile-device {
  position: absolute !important;
  top: 0;
  left: 0;
  display: block !important;
  width: 100%;
  height: 100% !important;
  opacity: 0;
}
/*# sourceMappingURL=bootstrap-select.css.map */