0x01000154:	xchgl 0x1005cfc, %esp
0x0100015a:	popa
0x0100015b:	xchgl %esp, %eax
0x0100015c:	pushl %ebp
0x0100015d:	movsb %es:(%edi), %ds:(%esi)
0x0100015e:	movb %dh, $0xffffff80<UINT8>
0x01000160:	call 0x010001e8
0x010001e8:	addb %dl, %dl
0x010001ea:	jne 0x010001f1
0x010001ec:	movb %dl, (%esi)
0x010001ee:	incl %esi
0x010001ef:	adcb %dl, %dl
0x010001f1:	ret

0x01000162:	jae 0x0100015d
0x01000164:	xorl %ecx, %ecx
0x01000166:	call 0x010001e8
0x01000168:	jae 0x01000180
0x0100016a:	xorl %eax, %eax
0x0100016c:	call 0x010001e8
0x0100016e:	jae 0x0100018f
0x01000170:	movb %dh, $0xffffff80<UINT8>
0x01000172:	incl %ecx
0x01000173:	movb %al, $0x10<UINT8>
0x01000175:	call 0x010001e8
0x01000177:	adcb %al, %al
0x01000179:	jae 0x01000175
0x0100017b:	jne 0x010001b7
0x0100017d:	stosb %es:(%edi), %al
0x0100017e:	jmp 0x01000160
0x0100018f:	lodsb %al, %ds:(%esi)
0x01000190:	shrl %eax
0x01000192:	je 0x010001c1
0x01000194:	adcl %ecx, %ecx
0x01000196:	jmp 0x010001b0
0x010001b0:	incl %ecx
0x010001b1:	incl %ecx
0x010001b2:	xchgl %ebp, %eax
0x010001b3:	movl %eax, %ebp
0x010001b5:	movb %dh, $0x0<UINT8>
0x010001b7:	pushl %esi
0x010001b8:	movl %esi, %edi
0x010001ba:	subl %esi, %eax
0x010001bc:	rep movsb %es:(%edi), %ds:(%esi)
0x010001be:	popl %esi
0x010001bf:	jmp 0x01000160
0x01000180:	call 0x010001de
0x010001de:	incl %ecx
0x010001df:	call 0x010001e8
0x010001e1:	adcl %ecx, %ecx
0x010001e3:	call 0x010001e8
0x010001e5:	jb 0x010001df
0x010001e7:	ret

0x01000183:	addb %dh, %dh
0x01000185:	sbbl %ecx, $0x1<UINT8>
0x01000188:	jne 0x01000198
0x0100018a:	call 0x010001dc
0x010001dc:	xorl %ecx, %ecx
0x0100018d:	jmp 0x010001b3
0x01000198:	xchgl %ecx, %eax
0x01000199:	decl %eax
0x0100019a:	shll %eax, $0x8<UINT8>
0x0100019d:	lodsb %al, %ds:(%esi)
0x0100019e:	call 0x010001dc
0x010001a1:	cmpl %eax, -8(%ebx)
0x010001a4:	jae 10
0x010001a6:	cmpb %ah, $0x5<UINT8>
0x010001a9:	jae 0x010001b1
0x010001ab:	cmpl %eax, $0x7f<UINT8>
0x010001ae:	ja 0x010001b2
0x010001c1:	popl %esi
0x010001c2:	lodsl %eax, %ds:(%esi)
0x010001c3:	xchgl %edi, %eax
0x010001c4:	lodsl %eax, %ds:(%esi)
0x010001c5:	pushl %eax
0x010001c6:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x010001c9:	xchgl %ebp, %eax
0x010001ca:	movl %eax, (%edi)
0x010001cc:	incl %eax
0x010001cd:	js 0x010001c2
0x010001cf:	jne 0x010001d4
0x010001d4:	pushl %eax
0x010001d5:	pushl %ebp
0x010001d6:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x010001d9:	stosl %es:(%edi), %eax
0x010001da:	jmp 0x010001ca
0x010001d1:	jmp 0x010011d7
0x010011d7:	pushl $0x28<UINT8>
0x010011d9:	pushl $0x10010b0<UINT32>
0x010011de:	call 0x01001374
0x01001374:	pushl $0x10013c4<UINT32>
0x01001379:	movl %eax, %fs:0
0x0100137f:	pushl %eax
0x01001380:	movl %fs:0, %esp
0x01001387:	movl %eax, 0x10(%esp)
0x0100138b:	movl 0x10(%esp), %ebp
0x0100138f:	leal %ebp, 0x10(%esp)
0x01001393:	subl %esp, %eax
0x01001395:	pushl %ebx
0x01001396:	pushl %esi
0x01001397:	pushl %edi
0x01001398:	movl %eax, -8(%ebp)
0x0100139b:	movl -24(%ebp), %esp
0x0100139e:	pushl %eax
0x0100139f:	movl %eax, -4(%ebp)
0x010013a2:	movl -4(%ebp), $0xffffffff<UINT32>
0x010013a9:	movl -8(%ebp), %eax
0x010013ac:	ret

0x010011e3:	xorl %edi, %edi
0x010011e5:	pushl %edi
0x010011e6:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x010011ec:	cmpw (%eax), $0x5a4d<UINT16>
0x010011f1:	jne 31
0x010011f3:	movl %ecx, 0x3c(%eax)
0x010011f6:	addl %ecx, %eax
0x010011f8:	cmpl (%ecx), $0x4550<UINT32>
0x010011fe:	jne 18
0x01001200:	movzwl %eax, 0x18(%ecx)
0x01001204:	cmpl %eax, $0x10b<UINT32>
0x01001209:	je 0x0100122a
0x0100122a:	cmpl 0x74(%ecx), $0xe<UINT8>
0x0100122e:	jbe -30
0x01001230:	xorl %eax, %eax
0x01001232:	cmpl 0xe8(%ecx), %edi
0x01001238:	setne %al
0x0100123b:	movl -28(%ebp), %eax
0x0100123e:	movl -4(%ebp), %edi
0x01001241:	pushl $0x1<UINT8>
0x01001243:	call __set_app_type@
__set_app_type@: API Node	
0x01001249:	popl %ecx
0x0100124a:	orl 0x10021d0, $0xffffffff<UINT8>
0x01001251:	orl 0x10021d4, $0xffffffff<UINT8>
0x01001258:	call __p__fmode@
__p__fmode@: API Node	
0x0100125e:	movl %ecx, 0x100202c
0x01001264:	movl (%eax), %ecx
0x01001266:	call __p__commode@
__p__commode@: API Node	
0x0100126c:	movl %ecx, 0x1002028
0x01001272:	movl (%eax), %ecx
0x01001274:	movl %eax, 0x1001044
0x01001279:	movl %eax, (%eax)
0x0100127b:	movl 0x10021d8, %eax
0x01001280:	call 0x01001370
0x01001370:	xorl %eax, %eax
0x01001372:	ret

0x01001285:	cmpl 0x1002000, %edi
0x0100128b:	jne 0x01001299
0x01001299:	call 0x0100135e
0x0100135e:	pushl $0x30000<UINT32>
0x01001363:	pushl $0x10000<UINT32>
0x01001368:	call 0x010013be
0x010013be:	jmp _controlfp@
_controlfp@: API Node	
0x0100136d:	popl %ecx
0x0100136e:	popl %ecx
0x0100136f:	ret

0x0100129e:	pushl $0x1001088<UINT32>
0x010012a3:	pushl $0x1001084<UINT32>
0x010012a8:	call 0x01001358
0x01001358:	jmp _initterm@
_initterm@: API Node	
0x010012ad:	movl %eax, 0x1002024
0x010012b2:	movl -32(%ebp), %eax
0x010012b5:	leal %eax, -32(%ebp)
0x010012b8:	pushl %eax
0x010012b9:	pushl 0x1002020
0x010012bf:	leal %eax, -36(%ebp)
0x010012c2:	pushl %eax
0x010012c3:	leal %eax, -40(%ebp)
0x010012c6:	pushl %eax
0x010012c7:	leal %eax, -44(%ebp)
0x010012ca:	pushl %eax
0x010012cb:	call __getmainargs@
__getmainargs@: API Node	
0x010012d1:	movl -48(%ebp), %eax
0x010012d4:	pushl $0x1001080<UINT32>
0x010012d9:	pushl $0x100107c<UINT32>
0x010012de:	call 0x01001358
0x010012e3:	movl %eax, -36(%ebp)
0x010012e6:	movl %ecx, 0x1001064
0x010012ec:	movl (%ecx), %eax
0x010012ee:	pushl -36(%ebp)
0x010012f1:	pushl -40(%ebp)
0x010012f4:	pushl -44(%ebp)
0x010012f7:	call 0x010010dc
0x010010dc:	pushl %ebp
0x010010dd:	movl %ebp, %esp
0x010010df:	subl %esp, $0x400<UINT32>
0x010010e5:	pushl %ebx
0x010010e6:	pushl %esi
0x010010e7:	pushl %edi
0x010010e8:	pushl $0x1002040<UINT32>
0x010010ed:	pushl $0x101<UINT32>
0x010010f2:	call WSAStarup@
WSAStarup@: API Node	
0x010010f8:	cmpl %eax, $0xffffffff<UINT8>
0x010010fb:	jne 0x0100110e
0x010010fd:	call 0x100100c
0x0100110e:	movl %edi, 0xc(%ebp)
0x01001111:	xorl %ebx, %ebx
0x01001113:	movl %esi, $0x10010ac<UINT32>
0x01001118:	jmp 0x01001121
0x01001121:	pushl %esi
0x01001122:	pushl %edi
0x01001123:	pushl 0x8(%ebp)
0x01001126:	call 0x01001475
0x01001475:	pushl %esi
0x01001476:	movl %esi, 0x1002004
0x0100147c:	cmpl %esi, 0x8(%esp)
0x01001480:	movl 0x10021dc, $0x1002034<UINT32>
0x0100148a:	jl 0x01001494
0x0100148c:	orl %eax, $0xffffffff<UINT8>
0x01001494:	pushl %ebx
0x01001495:	movl %ebx, 0x10(%esp)
0x01001499:	movl %eax, (%ebx,%esi,4)
0x0100149c:	cmpb (%eax), $0x2d<UINT8>
0x0100149f:	pushl %edi
0x010014a0:	je 0x010014aa
0x010014a2:	orl %eax, $0xffffffff<UINT8>
0x010014aa:	movl %ecx, 0x1002008
0x010014b0:	movl %edi, 0x18(%esp)
0x010014b4:	addl %eax, %ecx
0x010014b6:	movb %cl, (%edi)
0x010014b8:	testb %cl, %cl
0x010014ba:	movb %dl, (%eax)
0x010014bc:	je 16
0x010014be:	cmpb %cl, %dl
0x010014c0:	je 7
0x010014c2:	incl %edi
0x010014c3:	movb %cl, (%edi)
0x010014c5:	testb %cl, %cl
0x010014c7:	jne 0x010014be
0x010014c9:	cmpb (%edi), $0x0<UINT8>
0x010014cc:	jne 24
0x010014ce:	movsbl %eax, %dl
0x010014d1:	pushl %eax
0x010014d2:	pushl $0x7d0<UINT32>
0x010014d7:	pushl $0x1<UINT8>
0x010014d9:	call 0x010013ca
0x010013ca:	pushl %ebp
0x010013cb:	movl %ebp, %esp
0x010013cd:	pushl %ecx
0x010013ce:	pushl %ecx
0x010013cf:	leal %eax, 0x10(%ebp)
0x010013d2:	movl -8(%ebp), %eax
0x010013d5:	leal %eax, -8(%ebp)
0x010013d8:	pushl %eax
0x010013d9:	pushl $0x0<UINT8>
0x010013db:	leal %eax, -4(%ebp)
0x010013de:	pushl %eax
0x010013df:	pushl $0x0<UINT8>
0x010013e1:	pushl 0xc(%ebp)
0x010013e4:	pushl 0x1002030
0x010013ea:	pushl $0x900<UINT32>
0x010013ef:	call FormatMessageA@kernel32.dll
FormatMessageA@kernel32.dll: API Node	
0x010013f5:	testl %eax, %eax
0x010013f7:	jne 0x010013fb
0x010013fb:	movl %eax, -4(%ebp)
0x010013fe:	leal %edx, 0x1(%eax)
0x01001401:	movb %cl, (%eax)
0x01001403:	incl %eax
0x01001404:	testb %cl, %cl
0x01001406:	jne 0x01001401
0x01001408:	pushl %esi
0x01001409:	subl %eax, %edx
0x0100140b:	movl %esi, %eax
0x0100140d:	pushl %esi
0x0100140e:	pushl -4(%ebp)
0x01001411:	pushl -4(%ebp)
0x01001414:	call CharToOemBuffA@user32.dll
CharToOemBuffA@user32.dll: API Node	
0x0100141a:	pushl %esi
0x0100141b:	pushl -4(%ebp)
0x0100141e:	pushl 0x8(%ebp)
0x01001421:	call 0x01001560
0x01001560:	jmp _write@
_write@: API Node	
0x01001426:	addl %esp, $0xc<UINT8>
0x01001429:	pushl -4(%ebp)
0x0100142c:	movl %esi, %eax
0x0100142e:	call LocalFree@kernel32.dll
LocalFree@kernel32.dll: API Node	
0x01001434:	movl %eax, %esi
0x01001436:	popl %esi
0x01001437:	leave
0x01001438:	ret

0x010014de:	addl %esp, $0xc<UINT8>
0x010014e1:	pushl $0x3f<UINT8>
0x010014e3:	popl %eax
0x010014e4:	jmp 0x0100155a
0x0100155a:	popl %edi
0x0100155b:	popl %ebx
0x0100155c:	popl %esi
0x0100155d:	ret $0xc<UINT16>

0x0100112b:	cmpl %eax, $0xffffffff<UINT8>
0x0100112e:	jne 0x0100111a
0x0100111a:	cmpb %al, $0x73<UINT8>
0x0100111c:	jne 0x01001142
0x01001142:	pushl $0x2710<UINT32>
0x01001147:	pushl $0x2<UINT8>
0x01001149:	call 0x010013ca
0x0100114e:	popl %ecx
0x0100114f:	popl %ecx
0x01001150:	pushl $0x1<UINT8>
0x01001152:	jmp 0x010011d0
0x010011d0:	call exit@
exit@: Exit Node	
0x010014a5:	jmp 0x0100155a
0x01001130:	movl %eax, 0x1002004
0x01001135:	cmpl (%edi,%eax,4), $0x0<UINT8>
0x01001139:	je 0x01001154
0x0100113b:	pushl $0x2714<UINT32>
0x01001154:	pushl $0x400<UINT32>
0x01001159:	leal %eax, -1024(%ebp)
0x0100115f:	pushl %eax
0x01001160:	call gethostname@
gethostname@: API Node	
0x01001166:	testl %eax, %eax
0x01001168:	jnl 0x0100117d
0x0100116a:	call GetLastError@kernel32.dll
0x0100117d:	testl %ebx, %ebx
0x0100117f:	je 0x01001199
0x01001199:	leal %eax, -1024(%ebp)
0x0100119f:	leal %edx, 0x1(%eax)
0x010011a2:	movb %cl, (%eax)
0x010011a4:	incl %eax
0x010011a5:	testb %cl, %cl
0x010011a7:	jne 0x010011a2
0x010011a9:	subl %eax, %edx
0x010011ab:	pushl %eax
0x010011ac:	leal %eax, -1024(%ebp)
0x010011b2:	pushl %eax
0x010011b3:	leal %eax, -1024(%ebp)
0x010011b9:	pushl %eax
0x010011ba:	call CharToOemBuffA@user32.dll
0x010011c0:	leal %eax, -1024(%ebp)
0x010011c6:	pushl %eax
0x010011c7:	call puts@
puts@: API Node	
0x010011cd:	popl %ecx
0x010011ce:	pushl $0x0<UINT8>
GetLastError@kernel32.dll: API Node	
0x01001170:	pushl %eax
0x01001171:	pushl $0x2712<UINT32>
0x01001176:	call 0x01001439
0x01001439:	pushl %ebp
0x0100143a:	movl %ebp, %esp
0x0100143c:	xorl %eax, %eax
0x0100143e:	pushl %eax
0x0100143f:	pushl %eax
0x01001440:	leal %ecx, 0x8(%ebp)
0x01001443:	pushl %ecx
0x01001444:	pushl %eax
0x01001445:	pushl 0x8(%ebp)
0x01001448:	pushl 0x1002030
0x0100144e:	pushl $0x900<UINT32>
0x01001453:	call FormatMessageA@kernel32.dll
0x01001459:	testl %eax, %eax
0x0100145b:	je 20
0x0100145d:	pushl 0xc(%ebp)
0x01001460:	pushl 0x8(%ebp)
0x01001463:	call 0x01001566
0x01001566:	jmp s_perror@
s_perror@: API Node	
0x01001468:	pushl 0x8(%ebp)
0x0100146b:	call LocalFree@kernel32.dll
0x01001471:	popl %ebp
0x01001472:	ret $0x8<UINT16>

0x0100117b:	jmp 0x01001150
0x01001140:	jmp 0x01001147
0x0100148f:	jmp 0x0100155c
