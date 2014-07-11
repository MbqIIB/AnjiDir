truncate table iib_qa.gefu_infile;
truncate table custom.customupload;
truncate table custom.iap_disbt;
truncate table custom.iap_laa_Details;
truncate table custom.customerror;
truncate table custom.iap_control_table;

select * from iib_qa.gefu_infile order by record_num

TEST - 1 PASS
TEST - 2 PASS
TEST - 3 PASS
TEST - 4 FAIL

begin
pk_iap_controls.update_test_cases('macro_test_cases.txt', 1);
pk_iap_controls.update_test_cases('macro_test_cases.txt', 2);
pk_iap_controls.update_test_cases('macro_test_cases.txt', 3);
pk_iap_controls.update_test_cases('macro_test_cases.txt', 4);
--commit;
END;

select * from customerror

select * from iap_control_table;
select * from customupload;

grant select on customerror to finesb

create synonym customerror for custom.customerror


select * from custome


