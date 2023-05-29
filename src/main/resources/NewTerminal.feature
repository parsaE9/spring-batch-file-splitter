#Feature: NewTerminalFeature
#
#  Scenario: newTerminalPopular
#    Given  اطلاعات_زیر_در_سیستم_نباشد
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#      # sample BANK output
#      | row_number | psp_code  | acceptor_code   | trace_code   | local_date | local_time | recive_date | IBAN                       | deposite_date | deposite_type | deposite_circle_number | terminal_type | proccess_type | card_type | amount_shaparak | reference_code | deposite_flag | terminal_code       | orig_txn_info
#      | 000000001  | 581672022 | 015441980820001 | 000012       | 1401/12/01 | 13:14:07   | 1401/12/01  | IR660750031611212000012616 | 1401/12/01    | N             | 1                      | POS           | PU            | O         | 000000300000    | 200402139435   | D             | 98080991            |
#      | 000000002  | 581672031 | 000010000057247 | 000038       | 1401/12/01 | 21:43:39   | 1401/12/01  | IR650750023110276000020154 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000000480000    | 234726000038   | D             | 05747060            | 00000000000000000000
#
#      # sample PSP output
#      | row_number | psp_code  | acceptor_code   | trace_code   | local_date | local_time | recive_date | IBAN                       | deposite_date | deposite_type | deposite_circle_number | terminal_type | proccess_type | card_type | amount_shaparak | reference_code | deposite_flag | acceptor_Commission | PSP_Commission       | PSP_NET_Commission | terminal_code
#      | 000000001  | 581672121 | 982022121100224 | 000000847172 | 1401/11/30 | 23:46:56   | 1401/11/30  | IR730190000000116916587004 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000010000000    | 125872847172   | D             | C000000000000       | C000000000000        | C000000000000      | 98124376
#      | 000000002  | 581672121 | 663020481201180 | 000000847155 | 1401/11/30 | 23:46:50   | 1401/11/30  | IR100190000000306564529005 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000000200000    | 125872847155   | D             | C000000000000       | C000000000000        | C000000000000      | 66057227
#
#
#      # sample batch file input
#      | 000000001  | 581672022 | 220014111000022 | 000000000003 | 1401/12/01 | 00:06:28   | 1401/12/01  | IR470600420302507312633001 | 1401/12/01    | N             | 1                      | POS           | PU            | O         | 000001000000    | 101284498238   | D             | C000000000000       | C000000000000        | C000000000000      | C000000000000 | 22048457 | 6037701545282771 |  |  |  |  |
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
##      | MerchantIdentifier | MerchantType |
##      | 1234567890         | real         |
##      | 12345678901        | legal        |
##      | 123456789          | foreign      |
#
#
#
#
#
#    When  درخواست_درج_پایانه_به_همراه_پذیرنده_شبا_فروشگاه_مشتری_با_مشخصات_زير_دريافت_مي_شود
#      | MerchantIdentifier | MerchantType | PostalCode | BusinessCategory | IIN | AcceptorType | AcceptorCode | TerminalId | IBAN | OrgTrackingNumber |
#      | 1234567890         | real         | any        | any              | any | any          | any          | any        | any  | any               |
#      | 12345678901        | legal        | any        | any              | any | any          | any          | any        | any  | any               |
#      | 123456789          | foreign      | any        | any              | any | any          | any          | any        | any  | any               |
#    Then  درخواست_های_ارسالی_وضعیت_زیر_را_در_اختیار_گرفته_اند
#      | MerchantIdentifier | MerchantType | RequestStatus | ShpTrackingNumber |
#      | 1234567890         | real         | Success(14)   | $01               |
#      | 12345678901        | legal        | Success(14)   | $02               |
#      | 123456789          | foreign      | Success(14)   | $03               |
#    And      نتيجه_اطلاعات_با_مشخصات_زير_بررسی_ميشود_و_عدم_درج_رکوردهای_تکراری_اضافی_یا_جایگزین_کنترل_میشود.
#      | MerchantIdentifier | MerchantType | PostalCode | BusinessCategory | IIN | AcceptorType | AcceptorCode | TerminalId | IBAN | count |
#      | 1234567890         | real         | any        | any              | any | any          | any          | any        | any  | 1     |
#      | 12345678901        | legal        | any        | any              | any | any          | any          | any        | any  | 1     |
#      | 123456789          | foreign      | any        | any              | any | any          | any          | any        | any  | 1     |
#
##
##  Scenario: 40
##    Given    اطلاعات_زیر_در_سیستم_نباشد
##      | PersonIdentifier | PersonType |
##      | 1234567890       | حقیقی      |
##
##    When          درخواست_درج_پایانه_به_همراه_پذیرنده_شبا_فروشگاه_مشتری_با_مشخصات_زير_دريافت_مي_شود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 |
##
##    And    استعلام_نماد_ناموفق_(سایت_فاقد_نماد)
##
##    Then    درخواست_های_ارسالی_وضعیت_زیر_را_در_اختیار_گرفته_اند
##      | PersonIdentifier | PersonType | RequestStatus | shpTrackingNumber |
##      | 1234567890       | حقیقی      | Fail(5)       | $01               |
##
##    And         نتيجه_اطلاعات_با_مشخصات_زير_بررسی_ميشود_و_عدم_درج_رکوردهای_تکراری_اضافی_یا_جایگزین_کنترل_میشود.
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       | Count |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 | 0     |
##
##  Scenario: 41
##    Given    اطلاعات_زیر_در_سیستم_نباشد
##      | PersonIdentifier | PersonType |
##      | 1234567890       | حقیقی      |
##
##    When          درخواست_درج_پایانه_به_همراه_پذیرنده_شبا_فروشگاه_مشتری_با_مشخصات_زير_دريافت_مي_شود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 |
##
##    And    استعلام_نماد_ناموفق_(عدم_انطباق_شخص)
##
##    Then    درخواست_های_ارسالی_وضعیت_زیر_را_در_اختیار_گرفته_اند
##      | PersonIdentifier | PersonType | RequestStatus | shpTrackingNumber |
##      | 1234567890       | حقیقی      | Fail(5)       | $01               |
##
##    And         نتيجه_اطلاعات_با_مشخصات_زير_بررسی_ميشود_و_عدم_درج_رکوردهای_تکراری_اضافی_یا_جایگزین_کنترل_میشود.
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       | Count |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 | 0     |
##
##
##  Scenario: 43
##    Given    اطلاعات_زیر_در_سیستم_نباشد
##      | PersonIdentifier | PersonType |
##      | 1234567890       | حقیقی      |
##
##    When          درخواست_درج_پایانه_به_همراه_پذیرنده_شبا_فروشگاه_مشتری_با_مشخصات_زير_دريافت_مي_شود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 |
##
##    Then    درخواست_های_ارسالی_وضعیت_زیر_را_در_اختیار_گرفته_اند
##      | PersonIdentifier | PersonType | RequestStatus | shpTrackingNumber |
##      | 1234567890       | حقیقی      | Success(14)   | $01               |
##
##    And         نتيجه_اطلاعات_با_مشخصات_زير_بررسی_ميشود_و_عدم_درج_رکوردهای_تکراری_اضافی_یا_جایگزین_کنترل_میشود_و_شناسه_یکتای_پایانه_به_درخواست_کننده_اعلام_میشود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber  | TerminalType | IBAN | TerminalUniqueNumber       | Count |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | Acceptor0000001 | 123456789012345 | 123          | IPG  | IR123456789012345678901234 | 1     |
##
##
##  Scenario: 44
##    Given    اطلاعات_زیر_در_سیستم_نباشد
##      | PersonIdentifier | PersonType |
##      | 1234567890       | حقیقی      |
##
##    When          درخواست_درج_پایانه_به_همراه_پذیرنده_شبا_فروشگاه_مشتری_با_مشخصات_زير_دريافت_مي_شود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber | TerminalType | IBAN                       |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | 123456789012345 | 123            | IPG          | IR123456789012345678901234 |
##
##    Then    درخواست_های_ارسالی_وضعیت_زیر_را_در_اختیار_گرفته_اند
##      | PersonIdentifier | PersonType | RequestStatus | shpTrackingNumber |
##      | 1234567890       | حقیقی      | Fail(5)       | $01               |
##
##    And         نتيجه_اطلاعات_با_مشخصات_زير_بررسی_ميشود_و_عدم_درج_رکوردهای_تکراری_اضافی_یا_جایگزین_کنترل_میشود_و_شناسه_یکتای_پایانه_به_درخواست_کننده_اعلام_میشود
##      | PersonIdentifier | PersonType | PostalCode | BusinessCategory | IIN       | AcceptorType | AcceptorCode    | TerminalNumber  | TerminalType | IBAN | TerminalUniqueNumber       | Count |
##      | 1234567890       | حقیقی      | 27410000   | 1462562211       | 581672021 | 0            | Acceptor0000001 | 123456789012345 | 123          | IPG  | IR123456789012345678901234 | 0     |
