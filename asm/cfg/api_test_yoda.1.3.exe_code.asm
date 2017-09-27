0x00404060:	pushl %ebp
0x00404061:	movl %ebp, %esp
0x00404063:	pushl %ebx
0x00404064:	pushl %esi
0x00404065:	pushl %edi
0x00404066:	pusha
0x00404067:	call 0x0040406c
0x0040406c:	popl %ebp
0x0040406d:	subl %ebp, $0x40286c<UINT32>
0x00404073:	movl %ecx, $0x40345d<UINT32>
0x00404078:	subl %ecx, $0x4028c6<UINT32>
0x0040407e:	movl %edx, %ebp
0x00404080:	addl %edx, $0x4028c6<UINT32>
0x00404086:	leal %edi, (%edx)
0x00404088:	movl %esi, %edi
0x0040408a:	xorl %eax, %eax
0x0040408c:	jmp 0x00404092
0x00404092:	lodsb %al, %ds:(%esi)
0x00404093:	rorb %al, $0xffffffdb<UINT8>
0x00404096:	jmp 0x00404099
0x00404099:	decb %al
0x0040409b:	addb %al, $0x21<UINT8>
0x0040409d:	decb %al
0x0040409f:	addb %al, %cl
0x004040a1:	stc
0x004040a2:	addb %al, $0x57<UINT8>
0x004040a4:	clc
0x004040a5:	addb %al, $0xffffff8a<UINT8>
0x004040a7:	stc
0x004040a8:	nop
0x004040a9:	rorb %al, $0xffffffd7<UINT8>
0x004040ac:	rolb %al, $0x10<UINT8>
0x004040af:	jmp 0x004040b2
0x004040b2:	rorb %al, $0x5b<UINT8>
0x004040b5:	clc
0x004040b6:	xorb %al, $0xffffffd6<UINT8>
0x004040b8:	stc
0x004040b9:	xorb %al, $0xffffffd4<UINT8>
0x004040bb:	subb %al, %cl
0x004040bd:	jmp 0x004040c0
0x004040c0:	subb %al, $0xffffff8b<UINT8>
0x004040c2:	clc
0x004040c3:	stosb %es:(%edi), %al
0x004040c4:	loop 0x00404092
0x004040c6:	movl %edx, %ebp
0x004040c8:	addl %edx, $0x40321f<UINT32>
0x004040ce:	movl %eax, 0x20(%esp)
0x004040d2:	incl %eax
0x004040d3:	js 8
0x004040d5:	movl (%edx), $0x1<UINT32>
0x004040db:	jmp 0x004040e3
0x004040e3:	movl %edx, %ebp
0x004040e5:	addl %edx, $0x402866<UINT32>
0x004040eb:	leal %eax, (%edx)
0x004040ed:	movl %ecx, $0x403065<UINT32>
0x004040f2:	subl %ecx, $0x402866<UINT32>
0x004040f8:	call 0x00404401
0x00404401:	movl %edi, %eax
0x00404403:	xorl %eax, %eax
0x00404405:	xorl %ebx, %ebx
0x00404407:	xorl %edx, %edx
0x00404409:	movb %al, (%edi)
0x0040440b:	mull %eax, %edx
0x0040440d:	addl %ebx, %eax
0x0040440f:	incl %edx
0x00404410:	incl %edi
0x00404411:	loop 0x00404409
0x00404413:	xchgl %ebx, %eax
0x00404414:	ret

0x004040fd:	movl %edx, %ebp
0x004040ff:	addl %edx, $0x40321b<UINT32>
0x00404105:	movl (%edx), %eax
0x00404107:	movl %edx, %ebp
0x00404109:	addl %edx, $0x403213<UINT32>
0x0040410f:	testl (%edx), $0x1<UINT32>
0x00404115:	je 72
0x00404117:	movl %edx, %ebp
0x00404119:	addl %edx, $0x40338b<UINT32>
0x0040411f:	leal %esi, (%edx)
0x00404121:	movl %edx, %ebp
0x00404123:	addl %edx, $0x40294c<UINT32>
0x00404129:	leal %eax, (%edx)
0x0040412b:	movl %ds:0x8(%esi), %eax
0x0040412f:	movl %edi, %ebp
0x00404131:	movl %edx, %ebp
0x00404133:	addl %edx, $0x403195<UINT32>
0x00404139:	leal %eax, (%edx)
0x0040413b:	xorl %ebx, %ebx
0x0040413d:	pushl %eax
0x0040413e:	pushl %fs:(%ebx)
0x00404141:	movl %fs:(%ebx), %esp
0x00404144:	movw %ax, $0x4<UINT16>
0x00404148:	jmp 0x0040414b
0x0040414b:	int3
0x00404995:	pushl %ebp
0x00404996:	movl %ebp, %esp
0x00404998:	pushl %edi
0x00404999:	movl %eax, %ss:0x10(%ebp)
0x0040499d:	movl %edi, %ds:0x9c(%eax)
0x004049a4:	movl %edx, %edi
0x004049a6:	addl %edx, $0x403393<UINT32>
0x004049ac:	pushl %ds:(%edx)
0x004049af:	popl %ds:0xb8(%eax)
0x004049b6:	movl %ds:0xb4(%eax), %edi
0x004049bd:	movl %ds:0xb0(%eax), $0x4<UINT32>
0x004049c8:	movl %eax, $0x0<UINT32>
0x004049cd:	popl %edi
0x004049ce:	leave
0x004049cf:	ret

0x0040414c:	movl %ebp, %edi
0x0040414e:	xorl %ebx, %ebx
0x00404150:	popl %fs:(%ebx)
0x00404153:	addl %esp, $0x4<UINT8>
0x00404156:	cmpb %al, $0x4<UINT8>
0x00404158:	je 0x0040415f
0x0040415f:	movl %edx, %ebp
0x00404161:	addl %edx, $0x40320b<UINT32>
0x00404167:	movl %eax, (%edx)
0x00404169:	addl %eax, 0x3c(%eax)
0x0040416c:	addl %eax, $0x80<UINT32>
0x00404171:	movl %ecx, (%eax)
0x00404173:	addl %ecx, (%edx)
0x00404175:	addl %ecx, $0x10<UINT8>
0x00404178:	movl %eax, (%ecx)
0x0040417a:	addl %eax, (%edx)
0x0040417c:	movl %ebx, (%eax)
0x0040417e:	movl %edx, %ebp
0x00404180:	addl %edx, $0x403397<UINT32>
0x00404186:	movl (%edx), %ebx
0x00404188:	addl %eax, $0x4<UINT8>
0x0040418b:	movl %ebx, (%eax)
0x0040418d:	movl %edx, %ebp
0x0040418f:	addl %edx, $0x40339b<UINT32>
0x00404195:	movl (%edx), %ebx
0x00404197:	movl %edx, %ebp
0x00404199:	addl %edx, $0x40339f<UINT32>
0x0040419f:	leal %eax, (%edx)
0x004041a1:	pushl %eax
0x004041a2:	movl %edx, %ebp
0x004041a4:	addl %edx, $0x403397<UINT32>
0x004041aa:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x004041ac:	movl %edx, %ebp
0x004041ae:	addl %edx, $0x4033ac<UINT32>
0x004041b4:	movl %esi, %eax
0x004041b6:	movl (%edx), %eax
0x004041b8:	movl %edx, %ebp
0x004041ba:	addl %edx, $0x4033b0<UINT32>
0x004041c0:	leal %eax, (%edx)
0x004041c2:	call 0x004042a5
0x004042a5:	pushl %eax
0x004042a6:	pushl %esi
0x004042a7:	movl %edx, %ebp
0x004042a9:	addl %edx, $0x40339b<UINT32>
0x004042af:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x004042b1:	ret

0x004041c7:	movl %edx, %ebp
0x004041c9:	addl %edx, $0x4033c1<UINT32>
0x004041cf:	movl (%edx), %eax
0x004041d1:	movl %edx, %ebp
0x004041d3:	addl %edx, $0x4033c5<UINT32>
0x004041d9:	leal %eax, (%edx)
0x004041db:	call 0x004042a5
0x004041e0:	movl %edx, %ebp
0x004041e2:	addl %edx, $0x4033d4<UINT32>
0x004041e8:	movl (%edx), %eax
0x004041ea:	movl %edx, %ebp
0x004041ec:	addl %edx, $0x4033d8<UINT32>
0x004041f2:	leal %eax, (%edx)
0x004041f4:	call 0x004042a5
0x004041f9:	movl %edx, %ebp
0x004041fb:	addl %edx, $0x4033eb<UINT32>
0x00404201:	movl (%edx), %eax
0x00404203:	movl %edx, %ebp
0x00404205:	addl %edx, $0x4033ef<UINT32>
0x0040420b:	leal %eax, (%edx)
0x0040420d:	call 0x004042a5
0x00404212:	movl %edx, %ebp
0x00404214:	addl %edx, $0x4033fb<UINT32>
0x0040421a:	movl (%edx), %eax
0x0040421c:	movl %edx, %ebp
0x0040421e:	addl %edx, $0x4033ff<UINT32>
0x00404224:	leal %eax, (%edx)
0x00404226:	call 0x004042a5
0x0040422b:	movl %edx, %ebp
0x0040422d:	addl %edx, $0x40340b<UINT32>
0x00404233:	movl (%edx), %eax
0x00404235:	movl %edx, %ebp
0x00404237:	addl %edx, $0x40340f<UINT32>
0x0040423d:	leal %eax, (%edx)
0x0040423f:	call 0x004042a5
0x00404244:	movl %edx, %ebp
0x00404246:	addl %edx, $0x40341a<UINT32>
0x0040424c:	movl (%edx), %eax
0x0040424e:	movl %edx, %ebp
0x00404250:	addl %edx, $0x40341e<UINT32>
0x00404256:	leal %eax, (%edx)
0x00404258:	call 0x004042a5
0x0040425d:	movl %edx, %ebp
0x0040425f:	addl %edx, $0x403427<UINT32>
0x00404265:	movl (%edx), %eax
0x00404267:	movl %edx, %ebp
0x00404269:	addl %edx, $0x40342b<UINT32>
0x0040426f:	leal %eax, (%edx)
0x00404271:	call 0x004042a5
0x00404276:	movl %edx, %ebp
0x00404278:	addl %edx, $0x403437<UINT32>
0x0040427e:	movl (%edx), %eax
0x00404280:	movl %edx, %ebp
0x00404282:	addl %edx, $0x40343b<UINT32>
0x00404288:	leal %eax, (%edx)
0x0040428a:	call 0x004042a5
0x0040428f:	movl %edx, %ebp
0x00404291:	addl %edx, $0x403447<UINT32>
0x00404297:	movl (%edx), %eax
0x00404299:	movl %edx, %ebp
0x0040429b:	addl %edx, $0x402ab2<UINT32>
0x004042a1:	leal %eax, (%edx)
0x004042a3:	pushl %eax
0x004042a4:	ret

0x004042b2:	movl %edx, %ebp
0x004042b4:	addl %edx, $0x403213<UINT32>
0x004042ba:	testl (%edx), $0x10<UINT32>
0x004042c0:	je 59
0x004042c2:	pushl %fs:0x30
0x004042c9:	popl %eax
0x004042ca:	testl %eax, %eax
0x004042cc:	js 15
0x004042ce:	movl %eax, 0xc(%eax)
0x004042d1:	movl %eax, 0xc(%eax)
0x004042d4:	movl 0x20(%eax), $0x1000<UINT32>
0x004042db:	jmp 0x004042fd
0x004042fd:	movl %edx, %ebp
0x004042ff:	addl %edx, $0x40320b<UINT32>
0x00404305:	movl %edi, (%edx)
0x00404307:	addl %edi, 0x3c(%edi)
0x0040430a:	movl %esi, (%edx)
0x0040430c:	movl %ecx, 0x54(%edi)
0x0040430f:	movl %edx, %ebp
0x00404311:	addl %edx, $0x403479<UINT32>
0x00404317:	leal %eax, (%edx)
0x00404319:	pushl %eax
0x0040431a:	pushl $0x4<UINT8>
0x0040431c:	pushl %ecx
0x0040431d:	movl %edx, %ebp
0x0040431f:	addl %edx, $0x40320b<UINT32>
0x00404325:	pushl (%edx)
0x00404327:	movl %edx, %ebp
0x00404329:	addl %edx, $0x4033d4<UINT32>
0x0040432f:	call VirtualProtect@Kernel32.dll
VirtualProtect@Kernel32.dll: API Node	
0x00404331:	movl %edx, %ebp
0x00404333:	addl %edx, $0x403213<UINT32>
0x00404339:	testl (%edx), $0x8<UINT32>
0x0040433f:	je 233
0x00404345:	pushl $0x104<UINT32>
0x0040434a:	movl %edx, %ebp
0x0040434c:	addl %edx, $0x403479<UINT32>
0x00404352:	leal %edi, (%edx)
0x00404354:	pushl %edi
0x00404355:	pushl $0x0<UINT8>
0x00404357:	movl %edx, %ebp
0x00404359:	addl %edx, $0x4033eb<UINT32>
0x0040435f:	call GetModuleFileNameA@Kernel32.dll
GetModuleFileNameA@Kernel32.dll: API Node	
0x00404361:	pushl $0x0<UINT8>
0x00404363:	pushl $0x80<UINT32>
0x00404368:	pushl $0x3<UINT8>
0x0040436a:	pushl $0x0<UINT8>
0x0040436c:	pushl $0x1<UINT8>
0x0040436e:	pushl $0x80000000<UINT32>
0x00404373:	pushl %edi
0x00404374:	movl %edx, %ebp
0x00404376:	addl %edx, $0x4033fb<UINT32>
0x0040437c:	call CreateFileA@Kernel32.dll
CreateFileA@Kernel32.dll: API Node	
0x0040437e:	cmpl %eax, $0xffffffff<UINT8>
0x00404381:	jne 0x0040438a
0x0040438a:	movl %edi, %eax
0x0040438c:	pushl $0x0<UINT8>
0x0040438e:	pushl %edi
0x0040438f:	movl %edx, %ebp
0x00404391:	addl %edx, $0x403437<UINT32>
0x00404397:	call GetFileSize@Kernel32.dll
GetFileSize@Kernel32.dll: API Node	
0x00404399:	movl %edx, $0x40347d<UINT32>
0x0040439e:	subl %edx, $0x403065<UINT32>
0x004043a4:	subl %eax, %edx
0x004043a6:	subl %eax, $0x2<UINT8>
0x004043a9:	xchgl %esi, %eax
0x004043aa:	pushl %esi
0x004043ab:	pushl $0x40<UINT8>
0x004043ad:	movl %edx, %ebp
0x004043af:	addl %edx, $0x40340b<UINT32>
0x004043b5:	call GlobalAlloc@Kernel32.dll
GlobalAlloc@Kernel32.dll: API Node	
0x004043b7:	cmpl %eax, $0x0<UINT8>
0x004043ba:	jne 0x004043be
0x004043be:	xchgl %ebx, %eax
0x004043bf:	pushl $0x0<UINT8>
0x004043c1:	movl %edx, %ebp
0x004043c3:	addl %edx, $0x403479<UINT32>
0x004043c9:	leal %eax, (%edx)
0x004043cb:	pushl %eax
0x004043cc:	pushl %esi
0x004043cd:	pushl %ebx
0x004043ce:	pushl %edi
0x004043cf:	movl %edx, %ebp
0x004043d1:	addl %edx, $0x403427<UINT32>
0x004043d7:	call ReadFile@Kernel32.dll
ReadFile@Kernel32.dll: API Node	
0x004043d9:	movl %eax, %ebx
0x004043db:	movl %ecx, %esi
0x004043dd:	pushl %ebx
0x004043de:	pushl %edi
0x004043df:	call 0x00404401
0x004043e4:	movl %edx, %ebp
0x004043e6:	addl %edx, $0x403217<UINT32>
0x004043ec:	movl (%edx), %eax
0x004043ee:	popl %edi
0x004043ef:	popl %ebx
0x004043f0:	movl %edx, %ebp
0x004043f2:	addl %edx, $0x402c15<UINT32>
0x004043f8:	leal %eax, (%edx)
0x004043fa:	pushl %eax
0x004043fb:	ret

0x00404415:	pushl %ebx
0x00404416:	movl %edx, %ebp
0x00404418:	addl %edx, $0x40341a<UINT32>
0x0040441e:	call GlobalFree@Kernel32.dll
GlobalFree@Kernel32.dll: API Node	
0x00404420:	xchgl %esi, %eax
0x00404421:	pushl %eax
0x00404422:	pushl %edi
0x00404423:	movl %edx, %ebp
0x00404425:	addl %edx, $0x403447<UINT32>
0x0040442b:	call CloseHandle@Kernel32.dll
CloseHandle@Kernel32.dll: API Node	
0x0040442d:	popl %eax
0x0040442e:	movl %edx, %ebp
0x00404430:	addl %edx, $0x40320b<UINT32>
0x00404436:	movl %eax, (%edx)
0x00404438:	movl %ebx, $0x1<UINT32>
0x0040443d:	call 0x00404494
0x00404494:	movl %edi, %eax
0x00404496:	addl %edi, 0x3c(%edi)
0x00404499:	movl %esi, %edi
0x0040449b:	addl %esi, $0xf8<UINT32>
0x004044a1:	xorl %edx, %edx
0x004044a3:	cmpl %ds:(%esi), $0x63727372<UINT32>
0x004044aa:	je 143
0x004044b0:	cmpl %ds:(%esi), $0x7273722e<UINT32>
0x004044b7:	je 130
0x004044bd:	cmpl %ds:(%esi), $0x6f6c6572<UINT32>
0x004044c4:	je 121
0x004044c6:	cmpl %ds:(%esi), $0x6c65722e<UINT32>
0x004044cd:	je 112
0x004044cf:	cmpl %ds:(%esi), $0x4379<UINT32>
0x004044d6:	je 0x0040453f
0x004044d8:	cmpl %ds:(%esi), $0x6164652e<UINT32>
0x004044df:	je 94
0x004044e1:	cmpl %ds:(%esi), $0x6164722e<UINT32>
0x004044e8:	je 0x0040453f
0x004044ea:	cmpl %ds:(%esi), $0x6164692e<UINT32>
0x004044f1:	je 76
0x004044f3:	cmpl %ds:(%esi), $0x736c742e<UINT32>
0x004044fa:	je 67
0x004044fc:	cmpl %ds:0x14(%esi), $0x0<UINT8>
0x00404501:	je 60
0x00404503:	cmpl %ds:0x10(%esi), $0x0<UINT8>
0x00404508:	je 53
0x0040450a:	pusha
0x0040450b:	movl %ecx, %ds:0x10(%esi)
0x0040450f:	orl %ebx, %ebx
0x00404511:	jne 0x00404520
0x00404520:	movl %esi, %ds:0xc(%esi)
0x00404524:	addl %esi, %eax
0x00404526:	call 0x0040444e
0x0040444e:	movl %edi, %esi
0x00404450:	jmp 0x00404456
0x00404456:	lodsb %al, %ds:(%esi)
0x00404457:	rolb %al, $0xffffff8d<UINT8>
0x0040445a:	addb %al, $0xffffffb5<UINT8>
0x0040445c:	addb %al, %cl
0x0040445e:	jmp 0x00404461
0x00404461:	clc
0x00404462:	stc
0x00404463:	addb %al, %cl
0x00404465:	xorb %al, $0x22<UINT8>
0x00404467:	decb %al
0x00404469:	decb %al
0x0040446b:	jmp 0x0040446e
0x0040446e:	stc
0x0040446f:	addb %al, %cl
0x00404471:	rolb %al, $0xffffffdc<UINT8>
0x00404474:	jmp 0x00404477
0x00404477:	jmp 0x0040447a
0x0040447a:	jmp 0x0040447d
0x0040447d:	nop
0x0040447e:	rolb %al, $0x12<UINT8>
0x00404481:	addb %al, $0xfffffff4<UINT8>
0x00404483:	subb %al, $0x20<UINT8>
0x00404485:	xorb %al, $0x59<UINT8>
0x00404487:	stosb %es:(%edi), %al
0x00404488:	loop 0x00404456
0x0040448a:	ret

0x0040452b:	movl %edx, %ebp
0x0040452d:	addl %edx, $0x402d3e<UINT32>
0x00404533:	leal %eax, (%edx)
0x00404535:	pushl %eax
0x00404536:	ret

0x0040453e:	popa
0x0040453f:	addl %esi, $0x28<UINT8>
0x00404542:	incl %edx
0x00404543:	cmpw %dx, %ds:0x6(%edi)
0x00404548:	jne 0x004044a3
0x0040454e:	ret

0x00404442:	movl %edx, %ebp
0x00404444:	addl %edx, $0x402d4f<UINT32>
0x0040444a:	leal %eax, (%edx)
0x0040444c:	pushl %eax
0x0040444d:	ret

0x0040454f:	movl %edx, %ebp
0x00404551:	addl %edx, $0x40320b<UINT32>
0x00404557:	movl %ebx, (%edx)
0x00404559:	movl %edx, %ebp
0x0040455b:	addl %edx, $0x40320f<UINT32>
0x00404561:	addl %ebx, (%edx)
0x00404563:	rorl %ebx, $0x7<UINT8>
0x00404566:	movl 0x10(%esp), %ebx
0x0040456a:	movl %edx, %ebp
0x0040456c:	addl %edx, $0x40310b<UINT32>
0x00404572:	leal %ebx, (%edx)
0x00404574:	movl 0x1c(%esp), %ebx
0x00404578:	movl %edx, %ebp
0x0040457a:	addl %edx, $0x40320b<UINT32>
0x00404580:	movl %edi, (%edx)
0x00404582:	addl %edi, 0x3c(%edi)
0x00404585:	movl %ebx, 0xc0(%edi)
0x0040458b:	cmpl %ebx, $0x0<UINT8>
0x0040458e:	je 0x0040459b
0x0040459b:	movl %edx, %ebp
0x0040459d:	addl %edx, $0x403217<UINT32>
0x004045a3:	movl %eax, (%edx)
0x004045a5:	orl %eax, %eax
0x004045a7:	je 17
0x004045a9:	movl %edx, %ebp
0x004045ab:	addl %edx, $0x403475<UINT32>
0x004045b1:	cmpl %eax, (%edx)
0x004045b3:	je 0x004045ba
0x004045ba:	movl %edx, %ebp
0x004045bc:	addl %edx, $0x403223<UINT32>
0x004045c2:	leal %esi, (%edx)
0x004045c4:	pushl %ebx
0x004045c5:	movl %ebx, %ebp
0x004045c7:	addl %ebx, $0x403213<UINT32>
0x004045cd:	testl (%ebx), $0x20<UINT32>
0x004045d3:	je 90
0x004045d5:	pushl %esi
0x004045d6:	movl %ebx, %ebp
0x004045d8:	addl %ebx, $0x403479<UINT32>
0x004045de:	leal %edi, (%ebx)
0x004045e0:	xorl %ecx, %ecx
0x004045e2:	cmpl %ds:0x4(%esi), $0x0<UINT8>
0x004045e7:	je 0x00404608
0x004045e9:	movl %edx, %ds:0x4(%esi)
0x004045ed:	movl %ebx, %ebp
0x004045ef:	addl %ebx, $0x40320b<UINT32>
0x004045f5:	addl %edx, (%ebx)
0x004045f7:	cmpl %ds:(%edx), $0x0<UINT8>
0x004045fb:	je 0x00404603
0x004045fd:	incl %ecx
0x004045fe:	addl %edx, $0x4<UINT8>
0x00404601:	jmp 0x004045f7
0x00404603:	addl %esi, $0xc<UINT8>
0x00404606:	jmp 0x004045e2
0x00404608:	xorl %edx, %edx
0x0040460a:	movl %eax, $0x5<UINT32>
0x0040460f:	mull %eax, %ecx
0x00404611:	pushl %eax
0x00404612:	pushl $0x0<UINT8>
0x00404614:	movl %ebx, %ebp
0x00404616:	addl %ebx, $0x40340b<UINT32>
0x0040461c:	call GlobalAlloc@Kernel32.dll
0x0040461e:	orl %eax, %eax
0x00404620:	jne 0x00404627
0x00404627:	movl %ds:(%edi), %eax
0x0040462a:	movl %ds:0x4(%edi), %eax
0x0040462e:	popl %esi
0x0040462f:	popl %ebx
0x00404630:	cmpl %ds:0x4(%esi), $0x0<UINT8>
0x00404635:	je 0x004047c5
0x0040463b:	movl %ebx, %ds:(%esi)
0x0040463e:	movl %edx, %ebp
0x00404640:	addl %edx, $0x40320b<UINT32>
0x00404646:	addl %ebx, (%edx)
0x00404648:	movl %eax, %ebx
0x0040464a:	call 0x0040465b
0x0040465b:	pushl %esi
0x0040465c:	pushl %edi
0x0040465d:	movl %esi, %eax
0x0040465f:	movl %edi, %eax
0x00404661:	lodsb %al, %ds:(%esi)
0x00404662:	rorb %al, $0x4<UINT8>
0x00404665:	stosb %es:(%edi), %al
0x00404666:	cmpb %ds:(%edi), $0x0<UINT8>
0x0040466a:	jne 0x00404661
0x0040466c:	popl %edi
0x0040466d:	popl %esi
0x0040466e:	ret

0x0040464f:	movl %edx, %ebp
0x00404651:	addl %edx, $0x402e6f<UINT32>
0x00404657:	leal %eax, (%edx)
0x00404659:	pushl %eax
0x0040465a:	ret

0x0040466f:	pushl %ebx
0x00404670:	movl %edx, %ebp
0x00404672:	addl %edx, $0x403397<UINT32>
0x00404678:	call LoadLibraryA@kernel32.dll
0x0040467a:	testl %eax, %eax
0x0040467c:	je 326
0x00404682:	pushl %edx
0x00404683:	pushl %eax
0x00404684:	movl %edx, %ebp
0x00404686:	addl %edx, $0x403213<UINT32>
0x0040468c:	testl (%edx), $0x4<UINT32>
0x00404692:	je 18
0x00404694:	movl %edx, %ebp
0x00404696:	addl %edx, $0x402ea6<UINT32>
0x0040469c:	leal %eax, (%edx)
0x0040469e:	pushl %eax
0x0040469f:	movl %eax, %ebx
0x004046a1:	jmp 0x00404987
0x00404987:	jmp 0x0040498e
0x0040498e:	cmpb %ds:(%eax), $0x0<UINT8>
0x00404992:	jne 0x00404989
0x00404989:	movb %ds:(%eax), $0x0<UINT8>
0x0040498d:	incl %eax
0x00404994:	ret

0x004046a6:	popl %ebx
0x004046a7:	popl %edx
0x004046a8:	movl %ecx, %ds:0x8(%esi)
0x004046ac:	orl %ecx, %ecx
0x004046ae:	jne 0x004046b4
0x004046b4:	pushl %ebx
0x004046b5:	movl %ebx, %ebp
0x004046b7:	addl %ebx, $0x40320b<UINT32>
0x004046bd:	addl %ecx, (%ebx)
0x004046bf:	movl %edx, %ds:0x4(%esi)
0x004046c3:	addl %edx, (%ebx)
0x004046c5:	popl %ebx
0x004046c6:	cmpl %ds:(%ecx), $0x0<UINT8>
0x004046ca:	je 0x004047bd
0x004046d0:	testl (%ecx), $0x80000000<UINT32>
0x004046d6:	jne 92
0x004046d8:	movl %eax, (%ecx)
0x004046da:	addl %eax, $0x2<UINT8>
0x004046dd:	pushl %ebx
0x004046de:	movl %ebx, %ebp
0x004046e0:	addl %ebx, $0x40320b<UINT32>
0x004046e6:	addl %eax, (%ebx)
0x004046e8:	popl %ebx
0x004046e9:	pushl %eax
0x004046ea:	call 0x0040465b
0x004046ef:	popl %eax
0x004046f0:	movl %edi, %eax
0x004046f2:	pushl %edx
0x004046f3:	pushl %ecx
0x004046f4:	pushl %eax
0x004046f5:	pushl %ebx
0x004046f6:	movl %edx, %ebp
0x004046f8:	addl %edx, $0x40339b<UINT32>
0x004046fe:	call GetProcAddress@kernel32.dll
0x00404700:	orl %eax, %eax
0x00404702:	jne 0x0040470b
0x0040470b:	popl %ecx
0x0040470c:	popl %edx
0x0040470d:	pushl %edx
0x0040470e:	pusha
0x0040470f:	movl %edx, %ebp
0x00404711:	addl %edx, $0x403213<UINT32>
0x00404717:	testb (%edx), $0x4<UINT8>
0x0040471a:	je 18
0x0040471c:	movl %edx, %ebp
0x0040471e:	addl %edx, $0x402f2e<UINT32>
0x00404724:	leal %eax, (%edx)
0x00404726:	pushl %eax
0x00404727:	movl %eax, %edi
0x00404729:	jmp 0x00404987
0x0040472e:	popa
0x0040472f:	popl %edx
0x00404730:	movl (%edx), %eax
0x00404732:	jmp 0x00404751
0x00404751:	pushl %ecx
0x00404752:	movl %ecx, %ebp
0x00404754:	addl %ecx, $0x403213<UINT32>
0x0040475a:	testl (%ecx), $0x20<UINT32>
0x00404760:	je 79
0x00404762:	movl %ecx, %ebp
0x00404764:	addl %ecx, $0x40321f<UINT32>
0x0040476a:	cmpl (%ecx), $0x0<UINT8>
0x0040476d:	je 20
0x0040476f:	cmpl %ebx, $0x70000000<UINT32>
0x00404775:	jb 8
0x00404777:	cmpl %ebx, $0x77ffffff<UINT32>
0x0040477d:	jbe 0x0040478d
0x0040478d:	pushl %edi
0x0040478e:	pushl %esi
0x0040478f:	movl %ecx, %ebp
0x00404791:	addl %ecx, $0x403479<UINT32>
0x00404797:	leal %edi, (%ecx)
0x00404799:	movl %esi, %ds:0x4(%edi)
0x0040479d:	movl (%edx), %esi
0x0040479f:	subl %eax, %esi
0x004047a1:	subl %eax, $0x5<UINT8>
0x004047a4:	movb (%esi), $0xffffffe9<UINT8>
0x004047a7:	movl 0x1(%esi), %eax
0x004047aa:	addl %ds:0x4(%edi), $0x5<UINT8>
0x004047af:	popl %esi
0x004047b0:	popl %edi
0x004047b1:	popl %ecx
0x004047b2:	addl %ecx, $0x4<UINT8>
0x004047b5:	addl %edx, $0x4<UINT8>
0x004047b8:	jmp 0x004046c6
0x004047bd:	addl %esi, $0xc<UINT8>
0x004047c0:	jmp 0x00404630
0x004047c5:	xorl %eax, %eax
0x004047c7:	incl %eax
0x004047c8:	cmpl %eax, $0x1<UINT8>
0x004047cb:	je 0x004047cf
0x004047cf:	movl %edx, %ebp
0x004047d1:	addl %edx, $0x403213<UINT32>
0x004047d7:	testl (%edx), $0x2<UINT32>
0x004047dd:	je 24
0x004047df:	movl %edx, %ebp
0x004047e1:	addl %edx, $0x40320b<UINT32>
0x004047e7:	movl %edi, (%edx)
0x004047e9:	addl %edi, 0x3c(%edi)
0x004047ec:	movl %esi, (%edx)
0x004047ee:	movl %ecx, 0x54(%edi)
0x004047f1:	movb (%esi), $0x0<UINT8>
0x004047f4:	incl %esi
0x004047f5:	loop 0x004047f1
0x004047f7:	movl %edx, %ebp
0x004047f9:	addl %edx, $0x402866<UINT32>
0x004047ff:	leal %eax, (%edx)
0x00404801:	movl %ecx, $0x403065<UINT32>
0x00404806:	subl %ecx, $0x402866<UINT32>
0x0040480c:	jmp 0x00404810
0x00404810:	call 0x00404401
0x00404815:	jmp 0x00404819
0x00404819:	movl %edx, %ebp
0x0040481b:	addl %edx, $0x40321b<UINT32>
0x00404821:	movl %ebx, (%edx)
0x00404823:	xorl %eax, %ebx
0x00404825:	je 0x0040482f
0x0040482f:	movl %edx, %ebp
0x00404831:	addl %edx, $0x403065<UINT32>
0x00404837:	leal %edi, (%edx)
0x00404839:	movl %esi, %edi
0x0040483b:	leal %edi, (%edx)
0x0040483d:	movl %ecx, $0x403182<UINT32>
0x00404842:	subl %ecx, $0x403065<UINT32>
0x00404848:	xorl %eax, %eax
0x0040484a:	lodsb %al, %ds:(%esi)
0x0040484b:	xorb %al, $0x79<UINT8>
0x0040484d:	subb %al, %cl
0x0040484f:	rolb %al, $0x2<UINT8>
0x00404852:	stosb %es:(%edi), %al
0x00404853:	loop 0x0040484a
0x00404855:	movl %edx, %ebp
0x00404857:	addl %edx, $0x403065<UINT32>
0x0040485d:	leal %eax, (%edx)
0x0040485f:	pushl %eax
0x00404860:	ret

0x00404865:	movl %edx, %ebp
0x00404867:	addl %edx, $0x40344b<UINT32>
0x0040486d:	leal %eax, (%edx)
0x0040486f:	pushl %eax
0x00404870:	movl %edx, %ebp
0x00404872:	addl %edx, $0x4033ac<UINT32>
0x00404878:	pushl (%edx)
0x0040487a:	movl %edx, %ebp
0x0040487c:	addl %edx, $0x40339b<UINT32>
0x00404882:	call GetProcAddress@kernel32.dll
0x00404884:	orl %eax, %eax
0x00404886:	je 8
0x00404888:	call IsDebuggerPresent@kernel32.dll
IsDebuggerPresent@kernel32.dll: API Node	
0x0040488a:	orl %eax, %eax
0x0040488c:	je 0x00404890
0x00404890:	movl %edx, %ebp
0x00404892:	addl %edx, $0x403213<UINT32>
0x00404898:	testl (%edx), $0x1<UINT32>
0x0040489e:	je 95
0x004048a0:	movl %edx, %ebp
0x004048a2:	addl %edx, $0x40338b<UINT32>
0x004048a8:	leal %esi, (%edx)
0x004048aa:	movl %edx, %ebp
0x004048ac:	addl %edx, $0x4030d7<UINT32>
0x004048b2:	leal %eax, (%edx)
0x004048b4:	movl %ds:0x8(%esi), %eax
0x004048b8:	xorl %ebx, %ebx
0x004048ba:	movl %edx, %ebp
0x004048bc:	addl %edx, $0x4031d0<UINT32>
0x004048c2:	leal %eax, (%edx)
0x004048c4:	pushl %eax
0x004048c5:	pushl %fs:(%ebx)
0x004048c8:	movl %fs:(%ebx), %esp
0x004048cb:	movl %edi, %ebp
0x004048cd:	movl %eax, $0x4400<UINT32>
0x004048d2:	jmp 0x004048d5
0x004048d5:	int $0x68<UINT8>
0x004049d0:	pushl %ebp
0x004049d1:	movl %ebp, %esp
0x004049d3:	pushl %edi
0x004049d4:	movl %eax, %ss:0x10(%ebp)
0x004049d8:	movl %edi, %ds:0x9c(%eax)
0x004049df:	movl %edx, %edi
0x004049e1:	addl %edx, $0x403393<UINT32>
0x004049e7:	pushl %ds:(%edx)
0x004049ea:	popl %ds:0xb8(%eax)
0x004049f1:	movl %ds:0xb4(%eax), %edi
0x004049f8:	movl %ds:0x9c(%eax), $0x0<UINT32>
0x00404a03:	movl %eax, $0x0<UINT32>
0x00404a08:	popl %edi
0x00404a09:	leave
0x00404a0a:	ret

0x004048d7:	xorl %ebx, %ebx
0x004048d9:	popw %fs:(%ebx)
0x004048dd:	addl %esp, $0x4<UINT8>
0x004048e0:	cmpw %di, $0x1297<UINT16>
0x004048e5:	je 14
0x004048e7:	cmpw %di, $0x1277<UINT16>
0x004048ec:	je 7
0x004048ee:	cmpw %di, $0x1330<UINT16>
0x004048f3:	jne 0x004048ff
0x004048ff:	movl %edx, %ebp
0x00404901:	addl %edx, $0x403143<UINT32>
0x00404907:	leal %eax, (%edx)
0x00404909:	pushl %eax
0x0040490a:	ret

0x00404943:	xorb %al, %al
0x00404945:	movl %edx, %ebp
0x00404947:	addl %edx, $0x402866<UINT32>
0x0040494d:	leal %edi, (%edx)
0x0040494f:	movl %ecx, $0x40310b<UINT32>
0x00404954:	subl %ecx, $0x402866<UINT32>
0x0040495a:	stosb %es:(%edi), %al
0x0040495b:	loop 0x0040495a
0x0040495d:	movl %edx, %ebp
0x0040495f:	addl %edx, $0x403182<UINT32>
0x00404965:	leal %edi, (%edx)
0x00404967:	movl %ecx, $0x40345d<UINT32>
0x0040496c:	subl %ecx, $0x403182<UINT32>
0x00404972:	stosb %es:(%edi), %al
0x00404973:	loop 0x00404972
0x00404975:	popa
0x00404976:	pushl %eax
0x00404977:	xorl %eax, %eax
0x00404979:	pushl %fs:(%eax)
0x0040497c:	movl %fs:(%eax), %esp
0x0040497f:	jmp 0x00404982
0x00404982:	addb (%eax), %al
0x0040490b:	pushl %ebp
0x0040490c:	movl %ebp, %esp
0x0040490e:	pushl %edi
0x0040490f:	movl %eax, %ss:0x10(%ebp)
0x00404913:	movl %edi, %ds:0xc4(%eax)
0x0040491a:	pushl %ds:(%edi)
0x0040491d:	xorl %edi, %edi
0x0040491f:	popl %fs:(%edi)
0x00404922:	addl %ds:0xc4(%eax), $0x8<UINT8>
0x0040492a:	movl %edi, %ds:0xa4(%eax)
0x00404931:	roll %edi, $0x7<UINT8>
0x00404934:	movl %ds:0xb8(%eax), %edi
0x0040493b:	movl %eax, $0x0<UINT32>
0x00404940:	popl %edi
0x00404941:	leave
0x00404942:	ret

0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp 0x0034d8a9
0x0034d8a9:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp 0x0034d84a
0x0034d84a:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp 0x0034d8b3
0x0034d8b3:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp 0x0034d840
0x0034d840:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp 0x0034d845
0x0034d845:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp 0x0034d85e
0x0034d85e:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp 0x0034d863
0x0034d863:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp 0x0034d859
0x0034d859:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp 0x0034d872
0x0034d872:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp 0x0034d8ae
0x0034d8ae:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp 0x0034d84f
0x0034d84f:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp 0x0034d88b
0x0034d88b:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp 0x0034d877
0x0034d877:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp 0x0034d89a
0x0034d89a:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp 0x0034d890
0x0034d890:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp 0x0034d86d
0x0034d86d:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp 0x0034d89f
0x0034d89f:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp 0x0034d868
0x0034d868:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp 0x0034d895
0x0034d895:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp 0x0034d8a4
0x0034d8a4:	jmp LoadLibraryA@kernel32.dll
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp 0x0034d886
0x0034d886:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp 0x0034d87c
0x0034d87c:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp 0x0034d8b8
0x0034d8b8:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp 0x0034d881
0x0034d881:	jmp GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp 0x0034d854
0x0034d854:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
